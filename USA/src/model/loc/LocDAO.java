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
	
	// 관광지 이름, 사진, 작성자 가져오기(평점순)
	public ArrayList<LocVO> getLocFileName(String loc_regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LocVO> list = new ArrayList<LocVO>();
		String sql = "SELECT LOC.LOC_NAME, LOC.LOC_FILENAME, LOC.LOC_REGNAME, LOC.LOC_WRITER, NVL(AVG(REV.REV_SCORE),0) FROM LOC left OUTER JOIN REV ON LOC.LOC_NAME=REV.REV_LOCNAME WHERE LOC_REGNAME = ? GROUP BY (LOC.LOC_NAME, LOC.LOC_FILENAME, LOC.LOC_REGNAME, LOC.LOC_WRITER) ORDER BY NVL(AVG(REV.REV_SCORE),0) DESC";
		
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
				vo.setLoc_writer(rs.getString("LOC_WRITER"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 모든 관광지 이름, 사진 가져오기
	public ArrayList<LocVO> getAllLocFileName() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LocVO> list = new ArrayList<LocVO>();
		String sql = "SELECT LOC_NAME, LOC_FILENAME FROM LOC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LocVO vo = new LocVO();
				vo.setLoc_name((rs.getString("LOC_NAME")));
				vo.setLoc_filename((rs.getString("LOC_FILENAME")));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 인기 관광지 3개 이름, 사진 가져오기
	public ArrayList<LocVO> getBestLocFileName() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LocVO> list = new ArrayList<LocVO>();
		String sql = "select loc_name, loc_filename from loc where LOC_NAME in (select rev_locname from ( select rownum rnum, r.* from ( SELECT AVG(REV_SCORE), REV_LOCNAME FROM REV GROUP BY REV_LOCNAME) r ) where rnum >= 1 and rnum <=3)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LocVO vo = new LocVO();
				vo.setLoc_name((rs.getString("LOC_NAME")));
				vo.setLoc_filename((rs.getString("LOC_FILENAME")));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 관광지 데이터 가져오기
	public LocVO getLocContents(String loc_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocVO vo = null;
		String sql = "SELECT LOC_NAME, LOC_CONTENTS, LOC_FILENAME, LOC_REGNAME, LOC_WRITER FROM LOC WHERE LOC_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new LocVO();
				vo.setLoc_name(rs.getString("LOC_NAME"));
				vo.setLoc_contents(rs.getString("LOC_CONTENTS"));
				vo.setLoc_filename(rs.getString("LOC_FILENAME"));
				vo.setLoc_regname(rs.getString("LOC_REGNAME"));
				vo.setLoc_writer(rs.getString("LOC_WRITER"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return vo;
	}
	
	// 관광지 수정할 데이터 가져오기
	public LocVO getLocModiContents(String loc_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocVO vo = null;
		String sql = "SELECT LOC_NAME, LOC_CONTENTS, LOC_FILENAME, LOC_REGNAME FROM LOC WHERE LOC_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_name);
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
	
	// 관광지 추가하기
	public int insertLocList(LocVO vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String  sql = "INSERT INTO LOC(LOC_NAME, LOC_WRITER, LOC_CONTENTS, LOC_REGNAME, LOC_FILENAME) VALUES(?, ?, ? ,? ,?)";
		
		String loc_name = vo.getLoc_name();
		String loc_writer = vo.getLoc_writer();
		String loc_contents = vo.getLoc_contents();
		String loc_regname = vo.getLoc_regname();
		String loc_filename = vo.getLoc_filename();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_name);
			pstmt.setString(2, loc_writer);
			pstmt.setString(3, loc_contents);
			pstmt.setString(4, loc_regname);
			pstmt.setString(5, loc_filename);
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return result;
	}
	
	// 관광지 삭제하기
	public int deleteLocList(String loc_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String  sql = "DELETE FROM LOC WHERE LOC_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_name);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관광지 수정하기
	public int updateLocList(LocVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String  sql = "UPDATE LOC SET LOC_CONTENTS=?,LOC_FILENAME=? WHERE LOC_NAME=?";
		
		String loc_name = vo.getLoc_name();
		String loc_contents = vo.getLoc_contents();
		String loc_filename = vo.getLoc_filename();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_contents);
			pstmt.setString(2, loc_filename);
			pstmt.setString(3, loc_name);
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return result;
	}
	
	// 관광지 사진 가져오기
	public String getLocFile(String loc_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String LOC_FILENAME = null;
		String sql = "SELECT LOC_FILENAME FROM LOC WHERE LOC_NAME = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LOC_FILENAME = rs.getString("LOC_FILENAME");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return LOC_FILENAME;
	}
}
