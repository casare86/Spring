package connections;

import java.sql.SQLException;

public class PoolConnectionTest {
	
	public static void main (String[] args) throws SQLException {
		MySQLConnectionFactory connectionFactory = new MySQLConnectionFactory();
		
		for (int i = 0; i < 15; i++) {
			connectionFactory.connect();
			System.out.println("Connection number: " + i);
		}
	}

}
