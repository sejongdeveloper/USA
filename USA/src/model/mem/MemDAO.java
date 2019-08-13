package model.mem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
   // 닉네임
   public String nickname(String mem_id) {
      String nickname = null;
      
      try {
         conn = getConnection();
         String sql = "select mem_name from mem where mem_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, mem_id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            nickname = rs.getString("mem_name");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
      } // try end
      
      return nickname;

   }
	
	// 로그인
	public boolean login(String mem_id, String mem_pwd) {
		boolean isLogin = false;
		try {
			conn = getConnection();
			String sql = "select mem_id from mem where mem_id = ? and mem_pwd = ? and mem_alive = 1";
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
	public int update(String mem_id, String calc, String mem_value) {
		int result = 0;
		
		try {
			conn = getConnection();
			String sql = "update mem set " + calc + " = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_value);
			pstmt.setString(2, mem_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		
		return result;
	} // update() end
	
	// 아이디 찾기
	public ArrayList<MemVO> id(String mem_name, String mem_addr) {
		ArrayList<MemVO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select mem_id, mem_cdate from mem where mem_name = ? and mem_addr = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_name);
			pstmt.setString(2, mem_addr);
			rs = pstmt.executeQuery();
			
			MemVO vo = null;
			while(rs.next()) {
				vo = new MemVO();
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_cdate(rs.getTimestamp("mem_cdate"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} // try end
		return list;
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
	
	// 비밀번호 변경
	public int update(String mem_id, String mem_pwd) {
		int result = 0;
		try {
			conn = getConnection();
			String sql = "update mem set mem_pwd = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_pwd);
			pstmt.setString(2, mem_id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		} 
		
		return result;
	} //update() end
	
	// 회원탈퇴
	public int delete(String mem_id) {
		int result = 0;
		try {
			conn = getConnection();
			String sql = "update mem set mem_alive = 0 where mem_id = ?";
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
	
	// 아이디 중복검사
	public boolean idValidate(String mem_id) {
		boolean isId = false;
		try {
			conn = getConnection();
			String sql = "select mem_id from mem where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isId = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		} 
		
		return isId;
	} // idValidate() end
	
		public void getPoint(String mem_id) {
	      try {
	         conn = getConnection();
	         String sql = "update mem set mem_point = mem_point - 10 where mem_id=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mem_id);
	         pstmt.executeQuery();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
	      } 
	      
	   }
	   
	   public boolean lostPoint(String mem_id) {
		   boolean result=false;
		   try {
			conn=getConnection();
			String sql="update mem set mem_point =(select mem_point from mem where mem_id=?)-100 where mem_id= ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_id);
			int result2=pstmt.executeUpdate();
		   if(result2==1) {
			   result=true;
		   }
		   
		   } catch (Exception e) {
			e.printStackTrace();
		}finally {
	         CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
	  }
		   return result;
		   
	  }
}
