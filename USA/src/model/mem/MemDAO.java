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
	
	// 로그인
	public boolean login(String mem_id, String mem_pwd) {
		boolean isLogin = false;
		try {
			conn = getConnection();
			String sql = "select mem_id from mem where mem_id = ? and mem_pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isLogin = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return isLogin;
	} // login() end
	
	// 회원수정 폼
	public MemVO update(String mem_id) {
		MemVO vo = null;
		try {
			conn = getConnection();
			String sql = "select mem_pwd, mem_ph, mem_name, mem_addr, mem_filename, mem_point from mem where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemVO();
				vo.setMem_id(mem_id);
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_ph(rs.getString("mem_ph"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_filename(rs.getString("mem_filename"));
				vo.setMem_point(rs.getInt("mem_point"));
				System.out.println("회원수정 정보갖고옴");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return vo;
	} // update() end
	
	// 회원수정 실행
	public int update(MemVO vo) {
		int result = 0;
		
		try {
			conn = getConnection();
			String sql = "update mem set mem_name = ?, mem_ph = ?, mem_addr = ?, mem_filename = ? where mem_id = ? and mem_pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMem_name());
			pstmt.setString(2, vo.getMem_ph());
			pstmt.setString(3, vo.getMem_addr());
			pstmt.setString(4, vo.getMem_filename());
			pstmt.setString(5, vo.getMem_id());
			pstmt.setString(6, vo.getMem_pwd());
			System.out.println("1:" + vo.getMem_name());
			System.out.println("2:" + vo.getMem_ph());
			System.out.println("3:" + vo.getMem_addr());
			System.out.println("4:" + vo.getMem_filename());
			System.out.println("5:" + vo.getMem_id());
			System.out.println("6:" + vo.getMem_pwd());
			result = pstmt.executeUpdate();
			System.out.println("result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return result;
	} // update() end
	
	// 아이디 찾기
	public String id(String mem_name, String mem_addr) {
		String mem_id = null;
		try {
			conn = getConnection();
			String sql = "select mem_id from mem where mem_name = ? and mem_addr = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_name);
			pstmt.setString(2, mem_addr);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem_id = rs.getString("mem_id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return mem_id;
	} // id() end
	
	// 비밀번호 찾기 
	public String pwd(String mem_id, String mem_name, String mem_ph) {
		String mem_pwd = null;
		
		try {
			conn = getConnection();
			String sql = "select mem_pwd from mem where mem_id = ? and mem_name = ? and mem_ph = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_name);
			pstmt.setString(3, mem_ph);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem_pwd = rs.getString("mem_pwd");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return mem_pwd;
	} // pwd() end
	
	// 회원탈퇴
	public int delete(String mem_id) {
		int result = 0;
		try {
			conn = getConnection();
			String sql = "delete from mem where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		} 
		
		return result;
	} // delete() end
}
