package model.loc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LocDAO {
	
	private static LocDAO instance = new LocDAO();
	
	public static LocDAO getInstance() {
		return instance;
	}
	
	private LocDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/BoardDB");
		
		return ds.getConnection();
	}
	
	// 관광명소 이름, 사진 가져오기
	public HashMap<String, String> getLocName(String loc_regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "SELECT LOC_NAME, LOC_FILENAME FROM LOC WHERE LOC_REGNAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String loc_name = rs.getString(rs.getString("LOC_NAME"));
				String loc_filename = rs.getString(rs.getString("LOC_FILENAME"));
				map.put(loc_name, loc_filename);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// 관광명소 데이터 가져오기
	public ArrayList<LocVO> getLocContents(String loc_regname){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LocVO> list = new ArrayList<LocVO>();
		String sql = "SELECT lOC_NAME, LOC_CONTENTS, LOC_FILENAME FROM REG WHERE LOC_REGNAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LocVO vo = new LocVO();
				vo.setLoc_name(rs.getString("LOC_NAME"));
				vo.setLoc_contents(rs.getString("LOC_CONTENTS"));
				vo.setLoc_filename(rs.getString("LOC_FILENAME"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 관광명소 리뷰 등록
	public int insert(LocVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT lOC_NAME, LOC_CONTENTS, LOC_FILENAME FROM REG WHERE LOC_REGNAME = ?";
		
		
		return 0;
	}
}
