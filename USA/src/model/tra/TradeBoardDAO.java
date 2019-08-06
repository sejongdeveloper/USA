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
        String opt = (String)listOpt.get("opt"); // 검색조건
        String condition = (String)listOpt.get("condition"); // 검색내용
        
        
        try {
            conn = getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)   //일반검색
            {
                sql.append("select count(*) from MEMBER_BOARD");
                pstmt = conn.prepareStatement(sql.toString());
                
                //스트링버퍼 비워주는 명령어.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목검색
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용검색
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용검색
            {
                sql.append("select count(*) from MEMBER_BOARD ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이 검색
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
	            
	            // �옄�룞 而ㅻ컠�쓣 false濡� �븳�떎.
	            conn.setAutoCommit(false);
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("INSERT INTO MEMBER_BOARD");
	            sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
	            sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_COUNT, BOARD_DATE)");
	            sql.append(" VALUES(?,?,?,?,?,?,?,?,?,sysdate)");
	 
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
	                //값이 참일떄만 저장후 커밋
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
	    
	    
	    // 湲�紐⑸줉 媛��졇�삤湲�
	    public ArrayList<TradeBoardVO> getBoardList(HashMap<String, Object> listOpt) throws UnsupportedEncodingException
	    {
	        ArrayList<TradeBoardVO> list = new ArrayList<TradeBoardVO>();
	        
	        String opt = (String)listOpt.get("opt"); // 조건
	        String condition = (String)listOpt.get("condition"); // 내용
	        int start = (Integer)listOpt.get("start"); // 시작글번호
	        int end=5;         //한번에 보여줄  글번호
	        int endNum=start+end-1;
	        System.out.println(condition+"而⑤뵒�뀡 �엯�땲�떎.");
	         try {
	            conn = getConnection();
	            StringBuffer sql = new StringBuffer();
	            
	            // 湲�紐⑸줉 �쟾泥대�� 蹂댁뿬以� �븣
	            if(opt == null)
	            {
	                // BOARD_RE_REF(洹몃９踰덊샇)�쓽 �궡由쇱감�닚 �젙�젹 �썑 �룞�씪�븳 洹몃９踰덊샇�씪 �븣�뒗
	                // BOARD_RE_SEQ(�떟蹂�湲� �닚�꽌)�쓽 �삤由꾩감�닚�쑝濡� �젙�젹 �븳 �썑�뿉
	                // 10媛쒖쓽 湲��쓣 �븳 �솕硫댁뿉 蹂댁뿬二쇰뒗(start踰덉㎏ 遺��꽣 start+9源뚯�) 荑쇰━
	                // desc : �궡由쇱감�닚, asc : �삤由꾩감�닚 ( �깮�왂 媛��뒫 )
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
	                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_RE_REF");
	                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
	                sql.append("FROM");
	                sql.append(" (select * from MEMBER_BOARD order by BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setInt(1, start);
	                pstmt.setInt(2, endNum);
	                
	                // StringBuffer瑜� 鍮꾩슫�떎.
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("0")) // �젣紐⑹쑝濡� 寃��깋
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
	                pstmt.setInt(3, endNum);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("1")) // �궡�슜�쑝濡� 寃��깋
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
	                pstmt.setInt(3, endNum);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("2")) // �젣紐�+�궡�슜�쑝濡� 寃��깋
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
	                pstmt.setInt(4, endNum);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("3")) // 湲��벖�씠濡� 寃��깋
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
	                pstmt.setInt(3, endNum);
	                
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
	            System.out.println("daO�뱾由щ땲");
	            
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
	













