package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public DB(String url, String nameDB, String login, String pass) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
					cn = DriverManager.getConnection(url + nameDB, login, pass);
					st = cn.createStatement();
				
		} catch (ClassNotFoundException e) {
			System.out.println("ошибка при загрузке драйвера " + e);
		}

	}

	public void update(String sql) throws SQLException {
		
			st.executeUpdate(sql);
		

	}

	public ResultSet query(String sql) throws SQLException {
		
			rs = st.executeQuery(sql);
		
		return rs;
	}

	public void ShowTable(ResultSet rs) throws SQLException {
		
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			while (rs.next() == true) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}	
		
	}
	
	public void close() throws SQLException {
		
			rs.close();
			cn.close();
		
	}
	
	public Connection getCn() {
		return cn;
	}
	

}
