package model.mem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class MemDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static MemDAO singleton = new MemDAO();

	private MemDAO() {}
	
	public static MemDAO getInstance() {
		return singleton;
	} // getInstance() end
	
	// DBCP(DataBaseConnectionPool)연결 객체생성
	public Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/USADB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	} // getConnection() end
	
	// 회원가입
	public int insert(MemVO vo) {
		int result = 0;
		try {
			System.out.println("dao: " + vo.getMem_id());
			conn = getConnection();
			String sql = "insert into mem(mem_id, mem_pwd, mem_ph, mem_name, mem_addr, mem_filename) values(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMem_id());
			pstmt.setString(2, vo.getMem_pwd());
			pstmt.setString(3, vo.getMem_ph());
			pstmt.setString(4, vo.getMem_name());
			pstmt.setString(5, vo.getMem_addr());
			pstmt.setString(6, vo.getMem_filename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return result;
	} // insert() end
	
	
}
