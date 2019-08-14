package model.qa;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class QABoardDAO {

	private DataSource dataFactory;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = null;

	private static QABoardDAO instance = new QABoardDAO();

	public static QABoardDAO getInstance() {
		if (instance == null)
			instance = new QABoardDAO();
		return instance;
	}

	public QABoardDAO() {
	}

	public Connection getConnection() throws Exception {

		Context initCtx = new InitialContext();
		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/USADB");

		return ds.getConnection();
	}

	             //총 boardlist가 몇개인지판단.
	public int getBoardListCount(HashMap<String, Object> listOpt) {
		int result = 0;
		String opt = (String) listOpt.get("opt"); // 검색조건 null이면 없음 0이면 제목 1이면 내용 2이면 제목+내용 3이면 글쓴이.
		String condition = (String) listOpt.get("condition"); // 검색내용

		try {
			conn = getConnection();
			String sql = "select count(*) from qa";

			if (opt == null || opt == "") // 일반검색
			{
			
					sql = "select count(*) from qa where  qa_alive=0";
					pstmt = conn.prepareStatement(sql);
				

			} else if (opt.equals("0")) // 제목검색
			{
				
					sql = "select count(*) from qa where qa_subject like ? and qa_alive=0";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, '%' + condition + '%');

				

			} else if (opt.equals("1")) // 내용검색
			{

				
					sql = "select count(*) from qa where qa_contents like ? and qa_alive=0  ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, '%' + condition + '%');
				

			} else if (opt.equals("2")) // 제목+내용검색
			{

				
				
					sql = "select count(*) from qa  where qa_subject like ? or qa_contents like ? and qa_alive=0";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, '%' + condition + '%');
					pstmt.setString(2, '%' + condition + '%');

				
			} else if (opt.equals("3")) // 글쓴이 검색
			{
				

					sql = "select count(*) from qa where qa_writer like ? and qa_alive=0";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, '%' + condition + '%');
				

			}

			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	} // end getBoardListCount

	public boolean boardInsert(QABoardVO vo) {
		boolean result = false;

		try {
			conn = getConnection();

			//자동 commit을 false로해서 값넣기가 실패면 commit하지않게.
			conn.setAutoCommit(false);

			String sql = "insert into qa(qa_num,qa_subject,qa_readcount,qa_writer,qa_filename,qa_contents,qa_alive) "
					+ " values(?,?,?,?,?,?,?)";

			int num = vo.getqa_num();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, vo.getqa_subject());
			pstmt.setInt(3, vo.getqa_readcount());
			pstmt.setString(4, vo.getqa_writer());
			pstmt.setString(5, vo.getqa_filename());
			pstmt.setString(6, vo.getqa_contents());
			pstmt.setInt(7, 0);
			// 값 받아오기

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				// 값이 참일떄만 저장후 커밋
				conn.commit();
			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	} 

				//총 게시물을 실질적으로 담는곳.
	public ArrayList<QABoardVO> getBoardList(HashMap<String, Object> listOpt) throws UnsupportedEncodingException {
		ArrayList<QABoardVO> list = new ArrayList<QABoardVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");  //db로부터 값을 가져올떄 형태를 정해줌.
		String opt = (String) listOpt.get("opt"); // 조건
		String condition = (String) listOpt.get("condition"); // 내용
		int start = (Integer) listOpt.get("start"); // 시작글번호유동적이니 action에서 처리
		int end = (Integer) listOpt.get("end"); // 한번에 보여줄 글번호   마찬가지로 listaction에서 처리.
		int endNum = start + end - 1;

		try {
			conn = getConnection();
			String sql = null;
				//이 조건일경우 검색조건이 없음.
			if (opt == null || opt == "") {
				//뭐를 검색해서 들어오지않았더라도 분류는 전체와 개별이 있으니. 그것을 처리.
			
					sql = "select *  from  (select rownum rnum,qa_num,qa_subject,qa_readcount,qa_writer , qa_filename,qa_contents,qa_alive ,qa_sysdate from(select * from qa where qa_alive=0  order by qa_num desc))where ?<=rnum and rnum<=? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, endNum);
			

			} else if (opt.equals("0")) //제목
			{
				
					sql = "select *  from  (select rownum rnum,qa_num,qa_subject,qa_readcount,qa_writer , qa_filename,qa_contents,"
							+ "    qa_alive ,qa_sysdate from(select * from qa where qa_alive=0 and qa_subject like ?  order by qa_num desc))where ?<=rnum and rnum<=? ";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + condition + "%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, endNum);
				

			} else if (opt.equals("1")) // 내용
			{
					sql = "select *  from  (select rownum rnum,qa_num,qa_subject,qa_readcount,qa_writer , qa_filename,qa_contents,"
							+ "    qa_alive,qa_sysdate  from(select * from qa where qa_alive=0 and qa_contents like ?  order by qa_num desc))where ?<=rnum and rnum<=? ";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + condition + "%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, endNum);

				}
			 else if (opt.equals("2")) //제목 내용
			{
				
					sql = "select *  from  (select rownum rnum,qa_num,qa_subject,qa_readcount,qa_writer , qa_filename,qa_contents,"
							+ "    qa_alive,qa_sysdate from(select * from qa where  qa_alive=0 and qa_contents like ? or qa_subject like ?  order by qa_num desc))where ?<=rnum and rnum<=?";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + condition + "%");
					pstmt.setString(2, "%" + condition + "%");
					pstmt.setInt(3, start);
					pstmt.setInt(4, endNum);


			} else if (opt.equals("3")) // 글쓴이
			{
					sql = "select *  from  (select rownum rnum,qa_num,qa_subject,qa_readcount,qa_writer , qa_filename,qa_contents,"
							+ "   qa_alive,qa_sysdate from(select * from qa where  qa_alive=0 and qa_writer like ? order by qa_num desc))where ?<=rnum and rnum<=? ";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + condition + "%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, endNum);

				

			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				QABoardVO vo = new QABoardVO();
				vo.setqa_num(rs.getInt("qa_num"));
				vo.setqa_writer(rs.getString("qa_writer"));
				vo.setqa_subject(rs.getString("qa_subject"));
				vo.setqa_contents(rs.getString("qa_contents"));
				vo.setqa_filename(rs.getString("qa_filename"));
				vo.setqa_readcount(rs.getInt("qa_readcount"));
				vo.setqa_alive(rs.getInt("qa_alive"));

				vo.setqa_sysdate(sdf.format(rs.getTimestamp("qa_sysdate")));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	} 

	
	// 사이트 MainPage에 삽니다 팝니다 각각 보여주기위해 만들어진것.
	

	// 게시글 읽었을떄 조회수처리.
	public boolean updateCount(int boardNum) throws SQLException {
		boolean result = false;

		try {
			conn = getConnection();

			conn.setAutoCommit(false);

			String sql = "update qa set qa_readcount=qa_readcount+1 where qa_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); 
			}
		} catch (Exception e) {
			try {
				conn.rollback(); 
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
		pstmt.close();
		conn.close();
		return result;
	} // end updateCount

				//특정 게시판을 들어갔을떄 그 내용을 불러오는것.
	public QABoardVO getDetail(int boardNum) throws SQLException {
		QABoardVO vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시:mm분");

		try {
			conn = getConnection();

			String sql = "select * from qa where qa_num =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new QABoardVO();
				vo.setqa_num(rs.getInt("qa_num"));
				vo.setqa_writer(rs.getString("qa_writer"));
				vo.setqa_subject(rs.getString("qa_subject"));
				vo.setqa_contents(rs.getString("qa_contents"));
				vo.setqa_filename(rs.getString("qa_filename"));
				vo.setqa_readcount(rs.getInt("qa_readcount"));
				vo.setqa_alive(rs.getInt("qa_alive"));
				vo.setqa_sysdate(sdf.format(rs.getTimestamp("qa_sysdate")));
			}
System.out.println("여기는 리스트 찍는페이지안오나요");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		rs.close();
		pstmt.close();
		conn.close();
		return vo;
	} // end getDetail()

	public boolean updateBoard(QABoardVO vo) {
		boolean result = false;

		try {
			conn = getConnection();
			conn.setAutoCommit(false); 

			String sql = "update qa set qa_subject= ?,qa_contents= ?,qa_filename=? where qa_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getqa_subject());
			pstmt.setString(2, vo.getqa_contents());
			pstmt.setString(3, vo.getqa_filename());
			pstmt.setInt(4, vo.getqa_num());
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); 
			}

		} catch (Exception e) {
			try {
				conn.rollback(); 
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}

		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} 
		//게시판 번호 생성..
	public int getSeq() throws SQLException {
		int result = 1;

		try {
			conn = getConnection();

			String sql = "SELECT count(*) FROM qa";

			pstmt = conn.prepareStatement(sql);
			// ���� ����
			rs = pstmt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		rs.close();
		pstmt.close();
		conn.close();
		return result;
	} // end getSeq

	//게시판 내용 삭제위한것.
	public boolean deletetraboard(int qanum) {

		String sql = null;
		int result = 0;
		boolean flag = false;
		try {

			conn = getConnection();
			sql = "update qa set qa_alive=1 where qa_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qanum);
			result = pstmt.executeUpdate();
			if (result == 1) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);

		}

		return flag;
	}

}
