package model.tra;

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

public class TradeBoardreplyDAO {

	 private DataSource dataFactory;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 private Connection conn=null;

	private static TradeBoardreplyDAO instance = new TradeBoardreplyDAO();
	
	public static TradeBoardreplyDAO getInstance() {
		if(instance==null)	instance=new TradeBoardreplyDAO();
		return instance;
	}
	
	public TradeBoardreplyDAO() {	}
	
	public Connection getConnection() throws Exception {
	
		
		Context initCtx = new InitialContext();
		DataSource  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	public int  getreplynum() {
		int replynum=0;
			try {
			conn=getConnection();
			String sql="select count(*) from  trarep";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				replynum=rs.getInt(1)+1;
			}
			CloseUtil.close(rs);
			CloseUtil.close(conn);
			CloseUtil.close(pstmt);
			
		} catch (Exception e) {
			
		}
			
		return replynum;
	}
	
	//답글일 경우 순서 
	public int  getreplynumlv(int traRepWriteRep,int traRepNumRef) {
		int tradeBoarReplyNum=0;
		try {
			conn=getConnection();
			String sql="select count(*) from trarep where trarep_writerrep=? and trarep_numref=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, traRepWriteRep);
			pstmt.setInt(2, traRepNumRef);
			
			if(rs.next()) tradeBoarReplyNum=rs.getInt(1)+1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);
		
		return tradeBoarReplyNum;
	}
	public boolean insertReply(TradeBoardreplyVO vo) {
		boolean result = false;
		System.out.println("등록은하냐");
		try {
			conn = getConnection();

			conn.setAutoCommit(false);
			
			String sql="insert into trarep(trarep_num,trarep_tranum,trarep_contents,trarep_writer,trarep_writerrep,trarep_numref,"
					+ "trarep_numref_lv,trarep_writerrepwriter) values(?,?,?,?,?,?,?,?)";
			//1댓글 고유번호
			//2게시판 번호
			//3내용
			//4작성자
			//5부모작성자 번호
			//6.대댓글 유무
			//7.대댓글 순서
			
			
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getTrarep_num());
			pstmt.setInt(2, vo.getTrarep_tranum());
			pstmt.setString(3, vo.getTrarep_contents());
			pstmt.setString(4, vo.getTrarep_writer());
			pstmt.setInt(5, vo.getTrarep_writerrep());
			pstmt.setInt(6, vo.getTrarep_numref());
			pstmt.setInt(7, vo.getTrarep_numref_lv());
			pstmt.setString(8, vo.getTrarep_writerrepwriter());
			
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
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		CloseUtil.close(rs);
		CloseUtil.close(conn);
		CloseUtil.close(pstmt);
	
		
		return result;
	}
	public ArrayList<TradeBoardreplyVO> getreplylist(int tradeboardnum) {
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		ArrayList<TradeBoardreplyVO> list= new ArrayList<TradeBoardreplyVO>();
		TradeBoardreplyVO vo;
		String sql=null;
		try {
			conn=getConnection();
			
			sql="select * from trarep  where trarep_tranum=? order by trarep_writerrep asc,trarep_numref asc,trarep_numref_lv asc,trarep_num desc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, tradeboardnum);
		
			rs=pstmt.executeQuery();
			while(rs.next()) {
				 vo=new TradeBoardreplyVO();
				//1댓글 고유번호
				 vo.setTrarep_num(rs.getInt("trarep_num"));
				//2게시판 번호
				 vo.setTrarep_tranum(rs.getInt("trarep_tranum"));
				 //3내용
				 vo.setTrarep_contents(rs.getString("trarep_contents"));
				//4작성자
				 vo.setTrarep_writer(rs.getString("trarep_writer"));
				//5부모작성자 번호
				 vo.setTrarep_writerrep(rs.getInt("trarep_writerrep"));
				//6.대댓글 유무
				 vo.setTrarep_numref(rs.getInt("trarep_numref"));
				 //6.1대댓글 이라면 누구의 대댓글인지
				 vo.setTrarep_writerrepwriter(rs.getString("trarep_writerrepwriter"));
				//7.대댓글 순서
				 vo.setTrarep_numref_lv(rs.getInt("trarep_numref_lv"));
				//8.시간
				vo.setTrarep_date(sdf.format(rs.getTimestamp("trarep_date")));
				//9.얼라이브
				vo.setTrarep_alive(rs.getInt("trarep_alive"));
		
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
	//아래부터는 보여줄 게시내용만.
	public ArrayList<TradeBoardreplyVO> getreplylist2(int tradeboardnum,int startreplynum,int endreplynum) {
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		ArrayList<TradeBoardreplyVO> list= new ArrayList<TradeBoardreplyVO>();
		TradeBoardreplyVO vo;
		String sql=null;
		try {
			conn=getConnection();
			
			sql="select *from(select rownum rnum,trarep_num,trarep_tranum,trarep_contents,trarep_writer,trarep_writerrep,trarep_numref,trarep_numref_lv,trarep_alive,trarep_writerrepwriter,trarep_date from(select * from trarep where  trarep_tranum=? order by trarep_writerrep asc,trarep_numref asc,trarep_numref_lv asc,trarep_num asc)) where ?<=rnum and rnum<=?";
			pstmt=conn.prepareStatement(sql);
			
			//1   tra num
			pstmt.setInt(1, tradeboardnum);
			//2  시작
			pstmt.setInt(2, startreplynum);
		
			//3 끝
			pstmt.setInt(3, endreplynum);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				 vo=new TradeBoardreplyVO();
				//1댓글 고유번호
				 vo.setTrarep_num(rs.getInt("trarep_num"));
				//2게시판 번호
				 vo.setTrarep_tranum(rs.getInt("trarep_tranum"));
				 //3내용
				 vo.setTrarep_contents(rs.getString("trarep_contents"));
				//4작성자
				 vo.setTrarep_writer(rs.getString("trarep_writer"));
				//5부모작성자 번호
				 vo.setTrarep_writerrep(rs.getInt("trarep_writerrep"));
				//6.대댓글 유무
				 vo.setTrarep_numref(rs.getInt("trarep_numref"));
				 //6.1대댓글 이라면 누구의 대댓글인지
				 vo.setTrarep_writerrepwriter(rs.getString("trarep_writerrepwriter"));
				//7.대댓글 순서
				 vo.setTrarep_numref_lv(rs.getInt("trarep_numref_lv"));
				//8.시간
				vo.setTrarep_date(sdf.format(rs.getTimestamp("trarep_date")));
				//9.얼라이브
				vo.setTrarep_alive(rs.getInt("trarep_alive"));
		
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
	
	
	
	public boolean replydelete(int num) {
		String sql=null;
		int result = 0;
		boolean flag=false;

		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			sql="update trarep set trarep_alive=1 where trarep_num=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result>0) {
				System.out.println("삭제 완료");
				conn.commit();
				flag=true;
			}else {
				flag=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
			CloseUtil.close(conn);
		}
		
		
		return flag;
	}
	public boolean replyupdate(int num,String content) {
		String sql=null;
		int result = 0;
		boolean flag=false;

		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			sql="update trarep set trarep_contents=? where trarep_num=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, num);
			result=pstmt.executeUpdate();
			if(result>0) {
				System.out.println("업데이트 완료");
				conn.commit();
				flag=true;
			}else {
				flag=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
			CloseUtil.close(conn);
		}
		
		
		return flag;
	}
}
