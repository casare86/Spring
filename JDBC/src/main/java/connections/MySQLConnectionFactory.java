package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLConnectionFactory {
	
	private static final String url = "jdbc:mysql://localhost/cursos?useTimezone=true&serverTimezone=UTC";
	private static final String user = "casare";
	private static final String password = "casare";
	
	public DataSource dataSource;
	
	public MySQLConnectionFactory() {
		//mchange.com/projects/c3p0 - lib
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		
		comboPooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = comboPooledDataSource;
		
	}
	
	public Connection connect() throws SQLException {
		System.out.println("Connecting to database...");
		//return DriverManager.getConnection(url, user, password);
		return this.dataSource.getConnection();
	}
	
	public static void closeCon(Connection connection) throws SQLException {
		System.out.println("Connection closed.");
		connection.close();
	}

	public static Connection staticConnect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
