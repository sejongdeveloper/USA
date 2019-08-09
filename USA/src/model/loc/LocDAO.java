package model.loc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class LocDAO {
	
	private static LocDAO instance = new LocDAO();
	
	public static LocDAO getInstance() {
		return instance;
	}
	
	private LocDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	// 관광명소 이름, 사진 가져오기
	public ArrayList<LocVO> getLocFileName(String loc_regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LocVO> list = new ArrayList<LocVO>();
		String sql = "SELECT LOC_NAME, LOC_FILENAME, LOC_REGNAME FROM LOC WHERE LOC_REGNAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LocVO vo = new LocVO();
				vo.setLoc_name((rs.getString("LOC_NAME")));
				vo.setLoc_filename((rs.getString("LOC_FILENAME")));
				vo.setLoc_regname(rs.getString("LOC_REGNAME"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 관광명소 데이터 가져오기
	public LocVO getLocContents(String loc_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocVO vo = null;
		String sql = "SELECT * FROM LOC WHERE LOC_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new LocVO();
				vo.setLoc_name(rs.getString("LOC_NAME"));
				vo.setLoc_contents(rs.getString("LOC_CONTENTS"));
				vo.setLoc_filename(rs.getString("LOC_FILENAME"));
				vo.setLoc_regname(rs.getString("LOC_REGNAME"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return vo;
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
