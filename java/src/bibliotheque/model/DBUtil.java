package bibliotheque.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBUtil {
	
	private static final String jdbc_driver = "org.sqlite.JDBC";
	private static String url = "jdbc:sqlite::resource:bibliotheque.db";
	private static Connection conn = null;
	
	public static Connection dbConnect() throws SQLException, ClassNotFoundException{
		if(conn == null) {
			try {
				Class.forName(jdbc_driver);
				conn = DriverManager.getConnection(url);

			} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e);
				conn = null;
			}
		}
		return conn;
	}
	
	public static void dbDisconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed())
			conn.close();	
		} catch (Exception e){
			
			throw e;
		}
	}
	
	public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			dbConnect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryStmt);
		} catch (SQLException e) {
			throw e;
		}
		return rs;
	}
}
