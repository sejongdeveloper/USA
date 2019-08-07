package model.tra;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class TradeBoardDAO {
	
	 private DataSource dataFactory;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 private Connection conn=null;

	private static TradeBoardDAO instance = new TradeBoardDAO();
	
	public static TradeBoardDAO getInstance() {
		if(instance==null)	instance=new TradeBoardDAO();
		return instance;
	}
	
	public TradeBoardDAO() {	}
	
	public Connection getConnection() throws Exception {
	
		
		Context initCtx = new InitialContext();
		DataSource  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	public int getBoardListCount(HashMap<String, Object> listOpt)
    {
        int result = 0;
        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        
        
        try {
            conn = getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD");
                pstmt = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_ID like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
        	
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


	
	 public boolean boardInsert(TradeBoardVO vo)
	    {
	        boolean result = false;
	        
	        try {
	            conn = getConnection();
	            
	            // 자동 커밋을 false로 한다.
	            conn.setAutoCommit(false);
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("INSERT INTO MEMBER_BOARD");
	            sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
	            sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_COUNT, BOARD_DATE)");
	            sql.append(" VALUES(?,?,?,?,?,?,?,?,?,sysdate)");
	 
	            // 시퀀스 값을 글번호와 그룹번호로 사용
	            int num = vo.getBoard_num();
	 
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            pstmt.setString(2, vo.getBoard_id());
	            pstmt.setString(3, vo.getBoard_subject());
	            pstmt.setString(4, vo.getBoard_content());
	            pstmt.setString(5, vo.getBoard_file());
	            pstmt.setInt(6, num);
	            pstmt.setInt(7, 0);
	            pstmt.setInt(8, 0);
	            pstmt.setInt(9, 0);
	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	                result = true;
	                // 완료시 커밋
	                conn.commit(); 
	            }
	            
	        } catch (Exception e) {
	            try {
	                conn.rollback();
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            } 
	            throw new RuntimeException(e.getMessage());
	        }finally {
	        	
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
	    } // end boardInsert();
	    
	    
	    // 글목록 가져오기
	    public ArrayList<TradeBoardVO> getBoardList(HashMap<String, Object> listOpt) throws UnsupportedEncodingException
	    {
	        ArrayList<TradeBoardVO> list = new ArrayList<TradeBoardVO>();
	        
	        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
	        String condition = (String)listOpt.get("condition"); // 검색내용
	        int start = (Integer)listOpt.get("start"); // 현재 페이지번호
	        int end=4;         //한번에 보여줄 페이지
	        System.out.println(condition+"컨디션 입니다.");
	         try {
	            conn = getConnection();
	            StringBuffer sql = new StringBuffer();
	            
	            // 글목록 전체를 보여줄 때
	            if(opt == null)
	            {
	                // BOARD_RE_REF(그룹번호)의 내림차순 정렬 후 동일한 그룹번호일 때는
	                // BOARD_RE_SEQ(답변글 순서)의 오름차순으로 정렬 한 후에
	                // 10개의 글을 한 화면에 보여주는(start번째 부터 start+9까지) 쿼리
	                // desc : 내림차순, asc : 오름차순 ( 생략 가능 )
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_RE_REF");
	                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
	                sql.append("FROM");
	                sql.append(" (select * from MEMBER_BOARD order by BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setInt(1, start);
	                pstmt.setInt(2, start+end);
	                
	                // StringBuffer를 비운다.
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("0")) // 제목으로 검색
	            {
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
	                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
	                sql.append("FROM ");
	                sql.append("(select * from MEMBER_BOARD where BOARD_SUBJECT like ? ");
	                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+end);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("1")) // 내용으로 검색
	            {
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
	                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
	                sql.append("FROM ");
	                sql.append("(select * from MEMBER_BOARD where BOARD_CONTENT like ? ");
	                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+end);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("2")) // 제목+내용으로 검색
	            {
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
	                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
	                sql.append("FROM ");
	                sql.append("(select * from MEMBER_BOARD where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? ");
	                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setString(2, "%"+condition+"%");
	                pstmt.setInt(3, start);
	                pstmt.setInt(4, start+end);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("3")) // 글쓴이로 검색
	            {
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
	                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
	                sql.append("FROM ");
	                sql.append("(select * from MEMBER_BOARD where BOARD_ID like ? ");
	                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+end);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                TradeBoardVO vo= new TradeBoardVO();
	                vo.setBoard_num(rs.getInt("BOARD_NUM"));
	                vo.setBoard_id(rs.getString("BOARD_ID"));
	                vo.setBoard_subject(rs.getString("BOARD_SUBJECT"));
	                vo.setBoard_content(rs.getString("BOARD_CONTENT"));
	                vo.setBoard_file(rs.getString("BOARD_FILE"));
	                vo.setBoard_count(rs.getInt("BOARD_COUNT"));
	                vo.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
	                vo.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
	                vo.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
	                vo.setBoard_date(rs.getDate("BOARD_DATE"));
	                list.add(vo);
	            }
	            System.out.println("daO들리니");
	            
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }finally {
	        	
	        	try {
	        		rs.close();
	        		pstmt.close();
	        		conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
	        
	        return list;
	    } // end getBoardList
	    
	    //updatecount
	    public boolean updateCount(int boardNum) throws SQLException
		{
			boolean result = false;
			
			try {
				conn = getConnection();
				
				conn.setAutoCommit(false);
				
				StringBuffer sql = new StringBuffer();
				sql.append("update MEMBER_BOARD set BOARD_COUNT = BOARD_COUNT+1 ");
				sql.append("where BOARD_NUM = ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, boardNum);
				
				int flag = pstmt.executeUpdate();
				if(flag > 0){
					result = true;
					conn.commit(); // �Ϸ�� Ŀ��
				}	
			} catch (Exception e) {
				try {
					conn.rollback(); // ������ �ѹ�
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
				throw new RuntimeException(e.getMessage());
			}
			pstmt.close();
			conn.close();
			return result;
		} // end updateCount
	    
	    public TradeBoardVO getDetail(int boardNum) throws SQLException
		{	
	    	TradeBoardVO board = null;
			
			try {
				conn = getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append("select * from MEMBER_BOARD where BOARD_NUM = ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, boardNum);
				
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					board = new TradeBoardVO();
					board.setBoard_num(boardNum);
					board.setBoard_id(rs.getString("BOARD_ID"));
					board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
					board.setBoard_content(rs.getString("BOARD_CONTENT"));
					board.setBoard_file(rs.getString("BOARD_FILE"));
					board.setBoard_count(rs.getInt("BOARD_COUNT"));
					board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
					board.setBoard_date(rs.getDate("BOARD_DATE"));
					board.setBoard_parent(rs.getInt("BOARD_PARENT"));
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			rs.close();
			pstmt.close();
			conn.close();
			return board;
		} // end getDetail()
	    
	    public boolean updateBoard(TradeBoardVO vo) 
		{
			boolean result = false;
			
			try{
				conn = getConnection();
				conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
				
				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE MEMBER_BOARD SET");
				sql.append(" BOARD_SUBJECT=?");
				sql.append(" ,BOARD_CONTENT=?");
				sql.append(" ,BOARD_FILE=?");
				sql.append(" ,BOARD_DATE=SYSDATE ");
				sql.append("WHERE BOARD_NUM=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, vo.getBoard_subject());
				pstmt.setString(2, vo.getBoard_content());
				pstmt.setString(3, vo.getBoard_file());
				pstmt.setInt(4, vo.getBoard_num());
				
				int flag = pstmt.executeUpdate();
				if(flag > 0){
					result = true;
					conn.commit(); // �Ϸ�� Ŀ��
				}
				
			} catch (Exception e) {
				try {
					conn.rollback(); // ������ �ѹ�
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		} // end updateBoard
	    
	    
	    
	    public int getSeq() throws SQLException
		{
			int result = 1;
			
			try {
				conn = getConnection();
				
				// ������ ���� �����´�. (DUAL : ������ ���� ������������ �ӽ� ���̺�)
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
				
				pstmt = conn.prepareStatement(sql.toString());
				// ���� ����
				rs = pstmt.executeQuery();
				
				if(rs.next())	result = rs.getInt(1);

			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			return result;	
		} // end getSeq
	    
	
	
	
	
}
	













