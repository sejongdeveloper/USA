package model.tra;

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
	public int getBoardListCount(HashMap<String, Object> listOpt,String tra_head)
    {
        int result = 0;
        String opt = (String)listOpt.get("opt"); // 검색조건
        String condition = (String)listOpt.get("condition"); // 검색내용
        System.out.println(tra_head+"여기는 리스트카운트dao안");
        
        try {
            conn = getConnection();
            String sql = "select count(*) from tra";
            
            if(opt == null||opt=="")   //일반검색
            {
            	if(tra_head.equals("팝니다")||tra_head.equals("삽니다")) {
            		sql="select count(*) from tra where tra_head = ? and tra_alive=0";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, tra_head);
            	}else {
            		sql = "select count(*) from tra where  tra_alive=0";
            		pstmt=conn.prepareStatement(sql);
            	}

                

            
            }
            else if(opt.equals("0")) // 제목검색
            {
            	if(tra_head.equals("팝니다")||tra_head.equals("삽니다")) {
                sql="select count(*) from tra where tra_subject like ? and tra_alive=0 and tra_head=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, tra_head);
            	}else {
            		sql="select count(*) from tra where tra_subject like ? and tra_alive=0";
            		pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, '%'+condition+'%');
            				
            	}
                
            }
            else if(opt.equals("1")) // 내용검색
            {
            	
            	if(tra_head.equals("팝니다")||tra_head.equals("삽니다")) {
            		
                sql="select count(*) from tra where tra_contents like ? and tra_alive=0 ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, '%'+condition+'%');
            	}else {
            		 sql="select count(*) from tra where tra_contents like ? and tra_alive=0 and tra_head=? ";
            		 pstmt = conn.prepareStatement(sql);
                     pstmt.setString(1, '%'+condition+'%');
            	}
                
            }
            else if(opt.equals("2")) // 제목+내용검색
            {
            	
            	if(tra_head.equals("팝니다")||tra_head.equals("삽니다")) {
            		sql="select count(*) from tra  where tra_subject like ? or tra_contents like ? and tra_alive=0 and tra_head=?";
            		 pstmt = conn.prepareStatement(sql);
                     pstmt.setString(1, '%'+condition+'%');
                     pstmt.setString(2, '%'+condition+'%');
                     pstmt.setString(3, tra_head);

            	}else {
                sql="select count(*) from tra  where tra_subject like ? or tra_contents like ? and tra_alive=0";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');

            	}
            }
            else if(opt.equals("3")) // 글쓴이 검색
            {
            	if(tra_head.equals("팝니다")||tra_head.equals("삽니다")) {
            		sql="select count(*) from tra where tra_writer like ? and tra_alive=0 and tra_head=?";
            		 pstmt.setString(1, '%'+condition+'%');
            		 pstmt.setString(2, tra_head);
            		
            	}else {
            		
                sql="select count(*) from tra where tra_writer like ? and tra_alive=0";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, '%'+condition+'%');
            	}
                
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
	            
	            String sql = "insert into tra(tra_num,tra_subject,tra_readcount,tra_writer,tra_filename,tra_contents,tra_alive,tra_head) "
	            		+ " values(?,?,?,?,?,?,?,?)";
	            
	            int num = vo.getTra_num();
	 
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, num);
	            pstmt.setString(2, vo.getTra_subject());
	            pstmt.setInt(3, vo.getTra_readcount());
	            pstmt.setString(4, vo.getTra_writer());
	            pstmt.setString(5, vo.getTra_filename());
	            pstmt.setString(6, vo.getTra_contents());
	            pstmt.setInt(7, 0);
	            pstmt.setString(8, vo.getTra_head());
	            //값 받아오기
	            
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
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
	        String opt = (String)listOpt.get("opt"); // 조건
	        String condition = (String)listOpt.get("condition"); // 내용
	        String tra_head=(String)listOpt.get("tra_head");
	        int start = (Integer)listOpt.get("start"); // 시작글번호
	        int end=(Integer)listOpt.get("end");       //한번에 보여줄  글번호
	        int endNum=start+end-1;
	        System.out.println(condition+"而⑤뵒�뀡 �엯�땲�떎.");
	        System.out.println("여기는 헤더이름"+tra_head);
	         try {
	            conn = getConnection();
	            String sql = null;
	            System.out.println(opt+"여기는 dao안 opt");
	            System.out.println(condition+"여기는 dao안 컨디션");
	            // 湲�紐⑸줉 �쟾泥대�� 蹂댁뿬以� �븣
	            if(opt == null||opt=="")
	            {
	            	
	            		if(tra_head.equals("전체")) {
	            			sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents,tra_head,tra_alive ,tra_sysdate from(select * from tra where tra_alive=0 order by tra_num desc))where ?<=rnum and rnum<=?";
	            			pstmt = conn.prepareStatement(sql);
	            			pstmt.setInt(1, start);
	            			pstmt.setInt(2, endNum);
	            		}else {
	            			
	            			sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents,tra_head,tra_alive ,tra_sysdate from(select * from tra where tra_alive=0 and tra_head = ? order by tra_num desc))where ?<=rnum and rnum<=? ";
	            			pstmt = conn.prepareStatement(sql);
	            			pstmt.setInt(1, start);
	            			pstmt.setInt(2, endNum);
	            			pstmt.setString(3, tra_head);
	            		}
	                
	                
	                
	                
	                System.out.println(sql);

	            }
	            else if(opt.equals("0")) // �젣紐⑹쑝濡� 寃��깋 where tra_alive=0
	            {
	            	if(tra_head.equals("전체")) {
	            	sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents," + 
	                		"    tra_head,tra_alive ,tra_sysdate from(select * from tra where tra_alive=0 and tra_subject like ? order by tra_num desc))where ?<=rnum and rnum<=? ";
	                
	                pstmt = conn.prepareStatement(sql);
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, endNum);
	            	}else {
	            		sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents," + 
		                		"    tra_head,tra_alive ,tra_sysdate from(select * from tra where tra_alive=0 and tra_subject like ? order by tra_num desc))where ?<=rnum and rnum<=? ";
		                
		                pstmt = conn.prepareStatement(sql);
		                pstmt.setString(1, "%"+condition+"%");
		                pstmt.setInt(2, start);
		                pstmt.setInt(3, endNum);
	            	}
	            	
	                
	            }
	            else if(opt.equals("1")) // �궡�슜�쑝濡� 寃��깋
	            {
	            	sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents," + 
	                		"    tra_head,tra_alive,tra_sysdate  from(select * from tra where tra_alive=0 and tra_contents like ? order by tra_num desc))where ?<=rnum and rnum<=? and ";
	                
	                pstmt = conn.prepareStatement(sql);
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, endNum);
	                
	            }
	            else if(opt.equals("2")) // �젣紐�+�궡�슜�쑝濡� 寃��깋
	            {
	            	sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents," + 
	                		"    tra_head,tra_alive,tra_sysdate from(select * from tra where  tra_alive=0 and tra_contents like ? or tra_subject like ? order by tra_num desc))where ?<=rnum and rnum<=?";
	                
	                pstmt = conn.prepareStatement(sql);
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setString(2, "%"+condition+"%");
	                pstmt.setInt(3, start);
	                pstmt.setInt(4, endNum);
	                
	            }
	            else if(opt.equals("3")) // 湲��벖�씠濡� 寃��깋
	            {
	            	sql="select *  from  (select rownum rnum,tra_num,tra_subject,tra_readcount,tra_writer , tra_filename,tra_contents," + 
	                		"    tra_head ,tra_alive,tra_sysdate from(select * from tra where  tra_alive=0 and tra_writer like ? order by tra_num desc))where ?<=rnum and rnum<=? ";
	                
	                pstmt = conn.prepareStatement(sql);
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, endNum);
	                
	            }
	            
	            rs=pstmt.executeQuery();
	            while(rs.next())
	            {
	            	System.out.println("rs안여기 타긴 타나여?");
	                TradeBoardVO vo= new TradeBoardVO();
	                vo.setTra_num(rs.getInt("tra_num"));
	                vo.setTra_writer(rs.getString("tra_writer"));
	                vo.setTra_subject(rs.getString("tra_subject"));
	                vo.setTra_contents(rs.getString("tra_contents"));
	                vo.setTra_filename(rs.getString("tra_filename"));
	                vo.setTra_readcount(rs.getInt("tra_readcount"));
	                vo.setTra_head(rs.getString("tra_head"));
	                vo.setTra_alive(rs.getInt("tra_alive"));
	                
	                vo.setTra_sysdate(sdf.format(rs.getTimestamp("tra_sysdate")));
	                System.out.println("적합함?");
	                list.add(vo);
	            }
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
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
	    
	    
	    //mainpage용
	    public ArrayList<TradeBoardVO> getMainBoardList(String tra_head) throws UnsupportedEncodingException
	    {
	        ArrayList<TradeBoardVO> list = new ArrayList<TradeBoardVO>();
	        
	       
	         try {
	            conn = getConnection();
	            String sql = null;
	         
	            			
	            sql="select * from  (select rownum r,tra_subject,tra_writer,tra_num  from(select * from tra where tra_alive=0 "
	            					+ " and tra_head= ? order by tra_num desc)) where r<=10 ";  //최신글 10개씩
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, tra_head);
	            			

	            
	            rs=pstmt.executeQuery();
	            while(rs.next())
	            {
	                TradeBoardVO vo= new TradeBoardVO();
	                vo.setTra_writer(rs.getString("tra_writer"));
	                vo.setTra_subject(rs.getString("tra_subject"));
	                vo.setTra_num(rs.getInt("tra_num"));
	                list.add(vo);
	            }
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
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
				
				String sql="update tra set tra_readcount=tra_readcount+1 where tra_num=?";
				
				pstmt = conn.prepareStatement(sql);
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
	    	TradeBoardVO vo = null;
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시:mm분");
			
			try {
				conn = getConnection();
				
				String sql= "select * from tra where tra_num =?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNum);
				
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					 	vo= new TradeBoardVO();
		                vo.setTra_num(rs.getInt("tra_num"));
		                vo.setTra_writer(rs.getString("tra_writer"));
		                vo.setTra_subject(rs.getString("tra_subject"));
		                vo.setTra_contents(rs.getString("tra_contents"));
		                vo.setTra_filename(rs.getString("tra_filename"));
		                vo.setTra_readcount(rs.getInt("tra_readcount"));
		                vo.setTra_head(rs.getString("tra_head"));
		                vo.setTra_alive(rs.getInt("tra_alive"));
		                vo.setTra_sysdate(sdf.format(rs.getTimestamp("tra_sysdate")));
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			rs.close();
			pstmt.close();
			conn.close();
			return vo;
		} // end getDetail()
	    
	    public boolean updateBoard(TradeBoardVO vo) 
		{
			boolean result = false;
			
			try{
				conn = getConnection();
				conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
				
				String sql="update tra set tra_subject= ?,tra_contents= ?,tra_filename=? where tra_num=?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTra_subject());
				pstmt.setString(2, vo.getTra_contents());
				pstmt.setString(3, vo.getTra_filename());
				pstmt.setInt(4, vo.getTra_num());
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
				String sql = "SELECT count(*) FROM tra";
				
				pstmt = conn.prepareStatement(sql);
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
	    
	   public boolean deletetraboard(int tranum) {
		   
		   String sql=null;
		   int result=0;
		   boolean flag=false;
		   try {
			   
			conn=getConnection();
			sql="update tra set tra_alive=1 where tra_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, tranum);
			result=pstmt.executeUpdate();
			if(result==1) {
				flag=true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
			
			
		}
		   
		   
		   
		   return flag;
	   }
	
	
	
	
}
	













