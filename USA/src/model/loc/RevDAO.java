package model.loc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RevDAO {

	private static RevDAO instance = new RevDAO();
	
	public static RevDAO getInstance() {
		return instance;
	}
	
	private RevDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/BoardDB");
		
		return ds.getConnection();
	}
	
	// 관광명소 삭제 안된 리뷰 데이터 가져오기
	public ArrayList<RevVO> getRevContents(String rev_locname){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RevVO> list = new ArrayList<RevVO>();
		String sql = "SELECT REV_NUM, REV_DATE, REV_WRITER, CONTENTS, REV_SCORE FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_locname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RevVO vo = new RevVO();
				vo.setRev_num(rs.getInt("REV_NUM"));
				vo.setRev_date(rs.getTimestamp("REV_DATE"));
				vo.setRev_writer(rs.getString("REV_WRITER"));
				vo.setRev_contents(rs.getString("CONTENTS"));
				vo.setRev_score(rs.getInt("REV_SCORE"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 관광명소 삭제 안된 리뷰 총 개수
	public int getAllCount(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) count = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	// 관광명소 삭제 안된 리뷰 총 평점
	public double getAllScore(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT AVG(*) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		double avg = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_locname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				avg = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return avg;
	}
	
	// 관광명소 삭제 안된 리뷰 각 점수 개수
	public HashMap<Integer, Integer> getEachCount(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		String sql = "SELECT REV_SCORE, COUNT(*) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_locname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getInt(1), rs.getInt(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// 관광명소 리뷰 등록
	public int insert(RevVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REV(REV_NUM, REV_DATE, REV_WRITER, REV_CONTENTS, REV_ALIVE, REV_SCORE, REV_LOCNAME) VALUES(REV_NUM.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		Timestamp rev_date = vo.getRev_date();
		String rev_writer = vo.getRev_writer();
		String rev_contents = vo.getRev_contents();
		int rev_alive = vo.getRev_alive();
		int rev_score = vo.getRev_score();
		String rev_locname = vo.getRev_locname();
		
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, rev_date);
			pstmt.setString(2, rev_writer);
			pstmt.setString(3, rev_contents);
			pstmt.setInt(4, rev_alive);
			pstmt.setInt(5, rev_score);
			pstmt.setString(6, rev_locname);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관광명소 리뷰 수정할 정보 읽기
	public RevVO getUpdateVO(int rev_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RevVO vo = null;
		String sql = "select rev_contents, rev_score from rev where rev_num = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new RevVO();
				vo.setRev_contents(rs.getString("rev_contents"));
				vo.setRev_score(rs.getInt("rev_score"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	// 관광명소 리뷰 수정
	public int update(RevVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update rev set rev_contents = ?, rev_score = ? where rev_num = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRev_contents());
			pstmt.setString(2, vo.getRev_contents());
			pstmt.setInt(3, vo.getRev_num());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관광명소 리뷰 삭제
	public int delete(int rev_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from rev where rev_num = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
