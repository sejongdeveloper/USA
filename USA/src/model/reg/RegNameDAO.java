package model.reg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class RegNameDAO {
	
	private static RegNameDAO instance = new RegNameDAO();
	
	public static RegNameDAO getInstance() {
		return instance;
	}
	
	private RegNameDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	// 해당하는 지역명테이블 데이터 가져오기
	public RegNameVO getRegNameContents(String regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RegNameVO vo = null;
		String sql = "SELECT * FROM REGNAME WHERE REGNAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new RegNameVO();
				vo.setRegName(rs.getString("regName"));
				vo.setRegNameEng(rs.getString("regNameEng"));
				vo.setRegGmt(rs.getString("regGmt"));
				vo.setRegFlight(rs.getInt("regFlight"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return vo;
	}
}
