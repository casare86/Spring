package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connections.MySQLConnectionFactory;

public class InsertReport {
	
	public static void main(String[] args) throws SQLException {
		Connection con =  new MySQLConnectionFactory().connect();
		try 
		{
			Statement stm = con.createStatement();
			boolean data = stm.execute("SELECT id, name, description FROM products");
			System.out.println("The products table is empty? " + !data );
			
			ResultSet result = stm.getResultSet();
			while(result.next()) {
				Integer id = result.getInt("id");
				String name = result.getString("NAME");//Column label
				//SQL column range starts at 1 not at 0 like arrays so Description is 3. id=1, name=2
				String desc = result.getString(3); //Column index
				System.out.println("Prod. " +id + " - Name: "+ name + " Desc: " + desc);
			}
			result.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("closing connection.");
			con.close();
		}
	}
}
