package model.reg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class RegDAO {
	
	private static RegDAO instance = new RegDAO();
	
	public static RegDAO getInstance() {
		return instance;
	}
	
	private RegDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	// 해당하는 지역테이블 데이터 가져오기
	public ArrayList<RegVO> getRegContents(String reg_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RegVO> list = new ArrayList<RegVO>();
		String sql = "SELECT * FROM REG WHERE REG_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reg_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RegVO vo = new RegVO();
				vo.setReg_num(rs.getInt("reg_num"));
				vo.setReg_name(rs.getString("reg_name"));
				vo.setReg_subject(rs.getString("reg_subject"));
				vo.setReg_contents(rs.getString("reg_contents"));
				vo.setReg_filename(rs.getString("reg_filename"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 해당하는 지역테이블 파일명 가져오기
	public ArrayList<RegVO> getRegFileName(String regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RegVO> list = new ArrayList<RegVO>();
		String sql = "SELECT REG_FILENAME, REG_NAME FROM REG WHERE REG_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RegVO vo = new RegVO();
				vo.setReg_name(rs.getString("REG_NAME"));
				vo.setReg_filename(rs.getString("REG_FILENAME"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
}
