package dao;

import java.sql.Connection;
import java.sql.SQLException;


public class GenericDao {
	
	protected Connection con;
	
	public GenericDao(Connection connection) {
		this.con = connection;
	}
	
	public Connection getCon() {
		return con;
	}
	
	public void setCon(Connection con) throws SQLException {
		this.con = con;
	}
}
