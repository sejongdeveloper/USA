package model.qa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class QABoardreplyDAO {
	private DataSource dataFactory;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = null;

	private static QABoardreplyDAO instance = new QABoardreplyDAO();

	public static QABoardreplyDAO getInstance() {
		if (instance == null)
			instance = new QABoardreplyDAO();
		return instance;
	}

	public QABoardreplyDAO() {
	}

	public Connection getConnection() throws Exception {

		Context initCtx = new InitialContext();
		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/USADB");

		return ds.getConnection();
	}

	
	//댓글 고유번호를 위한 메소드.
	public int getreplynum() {
		int replynum = 0;
		try {
			conn = getConnection();
			String sql = "select count(*) from  qarep";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				replynum = rs.getInt(1) + 1;  //가장 마지막 값 +1로 처리
			}
			CloseUtil.close(rs);
			CloseUtil.close(conn);
			CloseUtil.close(pstmt);

		} catch (Exception e) {

		}

		return replynum;
	}

	// 답글의  댓글 순서 LV얻기위한 것.
	public int getreplynumlv(int qaRepWriteRep, int qaRepNumRef) {
		int qadeBoarReplyNum = 0;
		try {
			conn = getConnection();
			String sql = "select count(*) from qarep where qarep_writerrep=? and qarep_numref=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qaRepWriteRep);
			pstmt.setInt(2, qaRepNumRef);

			if (rs.next())
				qadeBoarReplyNum = rs.getInt(1) + 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);

		return qadeBoarReplyNum;
	}

	//리플 등록. 댓글 결과에따라 true false정함.
	public boolean insertReply(QABoardreplyVO vo) {
		boolean result = false;
		System.out.println("등록은하냐");
		try {
			conn = getConnection();

			conn.setAutoCommit(false);

			String sql = "insert into qarep(qarep_num,qarep_qanum,qarep_contents,qarep_writer,qarep_writerrep,qarep_numref,"
					+ "qarep_numref_lv,qarep_writerrepwriter) values(?,?,?,?,?,?,?,?)";
			// 1댓글 고유번호
			// 2게시판 번호
			// 3내용
			// 4작성자
			// 5부모작성자 번호
			// 6.대댓글 유무
			// 7.대댓글 순서

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getqarep_num());
			pstmt.setInt(2, vo.getqarep_qanum());
			pstmt.setString(3, vo.getqarep_contents());
			pstmt.setString(4, vo.getqarep_writer());
			pstmt.setInt(5, vo.getqarep_writerrep());
			pstmt.setInt(6, vo.getqarep_numref());
			pstmt.setInt(7, vo.getqarep_numref_lv());
			pstmt.setString(8, vo.getqarep_writerrepwriter());

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); // �Ϸ�� Ŀ��
			}

		} catch (Exception e) {
			try {
				conn.rollback(); 
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);

		return result;
	}

	//페이징 처리하기 전 getreplylist 이제는 getreplylist2로 사용함
	public ArrayList<QABoardreplyVO> getreplylist(int qadeboardnum) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		ArrayList<QABoardreplyVO> list = new ArrayList<QABoardreplyVO>();
		QABoardreplyVO vo;
		String sql = null;
		try {
			conn = getConnection();

			sql = "select * from qarep  where qarep_qanum=? order by qarep_writerrep asc,qarep_numref asc,qarep_numref_lv asc,qarep_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qadeboardnum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QABoardreplyVO();
				// 1댓글 고유번호
				vo.setqarep_num(rs.getInt("qarep_num"));
				// 2게시판 번호
				vo.setqarep_qanum(rs.getInt("qarep_qanum"));
				// 3내용
				vo.setqarep_contents(rs.getString("qarep_contents"));
				// 4작성자
				vo.setqarep_writer(rs.getString("qarep_writer"));
				// 5부모작성자 번호
				vo.setqarep_writerrep(rs.getInt("qarep_writerrep"));
				// 6.대댓글 유무
				vo.setqarep_numref(rs.getInt("qarep_numref"));
				// 6.1대댓글 이라면 누구의 대댓글인지
				vo.setqarep_writerrepwriter(rs.getString("qarep_writerrepwriter"));
				// 7.대댓글 순서
				vo.setqarep_numref_lv(rs.getInt("qarep_numref_lv"));
				// 8.시간
				vo.setqarep_date(sdf.format(rs.getTimestamp("qarep_date")));
				// 9.얼라이브
				vo.setqarep_alive(rs.getInt("qarep_alive"));

				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);

		return list;
	}

	// 아래부터는 보여줄 게시내용만.
	public ArrayList<QABoardreplyVO> getreplylist2(int qadeboardnum, int startreplynum, int endreplynum) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		ArrayList<QABoardreplyVO> list = new ArrayList<QABoardreplyVO>();
		QABoardreplyVO vo;
		String sql = null;
		try {
			conn = getConnection();

			sql = "select *from(select rownum rnum,qarep_num,qarep_qanum,qarep_contents,qarep_writer,qarep_writerrep,qarep_numref,qarep_numref_lv,qarep_alive,qarep_writerrepwriter,qarep_date from(select * from qarep where  qarep_qanum=? order by qarep_writerrep asc,qarep_numref asc,qarep_numref_lv asc,qarep_num asc)) where ?<=rnum and rnum<=?";
			pstmt = conn.prepareStatement(sql);

			// 1 qa num
			pstmt.setInt(1, qadeboardnum);
			// 2 시작
			pstmt.setInt(2, startreplynum);

			// 3 끝
			pstmt.setInt(3, endreplynum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QABoardreplyVO();
				// 1댓글 고유번호
				vo.setqarep_num(rs.getInt("qarep_num"));
				// 2게시판 번호
				vo.setqarep_qanum(rs.getInt("qarep_qanum"));
				// 3내용
				vo.setqarep_contents(rs.getString("qarep_contents"));
				// 4작성자
				vo.setqarep_writer(rs.getString("qarep_writer"));
				// 5부모작성자 번호
				vo.setqarep_writerrep(rs.getInt("qarep_writerrep"));
				// 6.대댓글 유무
				vo.setqarep_numref(rs.getInt("qarep_numref"));
				// 6.1대댓글 이라면 누구의 대댓글인지
				vo.setqarep_writerrepwriter(rs.getString("qarep_writerrepwriter"));
				// 7.대댓글 순서
				vo.setqarep_numref_lv(rs.getInt("qarep_numref_lv"));
				// 8.시간
				vo.setqarep_date(sdf.format(rs.getTimestamp("qarep_date")));
				// 9.얼라이브
				vo.setqarep_alive(rs.getInt("qarep_alive"));

				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);

		return list;
	}
	//

	//댓글 고유번호만 가져와서 삭제.
	public boolean replydelete(int num) {
		String sql = null;
		int result = 0;
		boolean flag = false;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			sql = "update qarep set qarep_alive=1 where qarep_num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("삭제 완료");
				conn.commit();
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
			CloseUtil.close(conn);
		}

		return flag;
	}

	
	//수정을 위해 댓글 고유번호와 내용 받아옴.
	public boolean replyupdate(int num, String content) {
		String sql = null;
		int result = 0;
		boolean flag = false;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			sql = "update qarep set qarep_contents=? where qarep_num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("업데이트 완료");
				conn.commit();
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
			CloseUtil.close(conn);
		}

		return flag;
	}
}
