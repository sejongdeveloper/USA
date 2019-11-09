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

import dbclose.util.CloseUtil;

public class RevDAO {

	private static RevDAO instance = new RevDAO();
	
	public static RevDAO getInstance() {
		return instance;
	}
	
	private RevDAO() { }
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	// 해당 관광지 삭제 안된 리뷰 데이터 가져오기
	public ArrayList<RevVO> getRevContents(String rev_locname){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RevVO> list = new ArrayList<RevVO>();
		String sql = "SELECT REV_NUM, REV_DATE, REV_WRITER, REV_CONTENTS, REV_SCORE FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1 ORDER BY REV_NUM";
		
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
				vo.setRev_contents(rs.getString("REV_CONTENTS"));
				vo.setRev_score(rs.getInt("REV_SCORE"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 해당 관광지 삭제 안된 리뷰 총 개수 가져오기
	public int getAllCount(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_locname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) count = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return count;
	}
	
	// 해당 관광지 삭제 안된 리뷰 총 평점 가져오기
	public double getAllScore(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT AVG(REV_SCORE) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1";
		double avg = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_locname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				avg = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return avg;
	}
	
	// 지역의 모든 관광지 삭제 안된 리뷰 총 평점 가져오기(평점순)
	public ArrayList<Double> getAllLocScore(String loc_regname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Double> list = new ArrayList<Double>();
		String sql = "SELECT AVG(REV_SCORE) AVG, REV_LOCNAME FROM REV WHERE REV_LOCNAME IN(SELECT LOC_NAME FROM LOC WHERE LOC_REGNAME = ?) AND REV_ALIVE = 1 GROUP BY REV_LOCNAME ORDER BY AVG DESC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_regname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getDouble(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return list;
	}
	
	// 해당 관광지 삭제 안된 리뷰 각 점수 개수 가져오기
	public HashMap<Integer, Integer> getEachCount(String rev_locname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		String sql = "SELECT REV_SCORE, COUNT(*) FROM REV WHERE REV_LOCNAME = ? AND REV_ALIVE = 1 GROUP BY REV_SCORE";
		
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
		} finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return map;
	}
	
	// 관광지 리뷰 등록
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
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return result;
	}
	
	// 관광지 리뷰 수정
	public int update(RevVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REV SET REV_CONTENTS = ?, REV_SCORE = ? WHERE REV_NUM = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRev_contents());
			pstmt.setInt(2, vo.getRev_score());
			pstmt.setInt(3, vo.getRev_num());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return result;
	}
	
	// 관광지 리뷰 삭제
	public int delete(int rev_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REV SET REV_ALIVE = 0 WHERE REV_NUM = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return result;
	}
	
	
}
