package model.tra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class TradeBoardRepleDAO {

	 private DataSource dataFactory;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 private Connection conn=null;

	private static TradeBoardRepleDAO instance = new TradeBoardRepleDAO();
	
	public static TradeBoardRepleDAO getInstance() {
		if(instance==null)	instance=new TradeBoardRepleDAO();
		return instance;
	}
	
	public TradeBoardRepleDAO() {	}
	
	public Connection getConnection() throws Exception {
	
		
		Context initCtx = new InitialContext();
		DataSource  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	
	public boolean insertReple(TradeBoardRepleVO vo) {
		boolean result = false;
		
		try {
			conn = getConnection();

			// �ڵ� Ŀ���� false�� �Ѵ�.
			conn.setAutoCommit(false);
			
			String sql="insert into trarep(trarep_num,trarep_tranum,trarep_contents,trarep_writer,trarep_writerrep,trarep_numref,"
					+ "trarep_numref_lv) values(?,?,?,?,1,1,1)";
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
}
