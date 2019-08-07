package model.tra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TradeBoardRepleDAO {

	 private DataSource dataFactory;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 private Connection conn=null;

	private static TradeBoardRepleDAO instance = new TradeBoardRepleDAO();
	
	public static TradeBoardRepleDAO getInstance() {
		if(instance==null)	instance=new TradeBoardRepleDAO();
		return instance;
	}
	
	public TradeBoardRepleDAO() {	}
	
	public Connection getConnection() throws Exception {
	
		
		Context initCtx = new InitialContext();
		DataSource  ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/USADB");
		
		return ds.getConnection();
	}
	
	
	
}
