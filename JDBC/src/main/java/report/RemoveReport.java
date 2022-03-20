package report;

import java.sql.Connection;
import java.sql.SQLException;

import connections.MySQLConnectionFactory;
import dao.ProductDao;
import model.ProductModel;

public class RemoveReport {
	
	public static void main(String[] args) throws SQLException {
		Connection con =  new MySQLConnectionFactory().connect();
		try 
		{
			ProductDao productDao = new ProductDao(con);
			ProductModel product = new ProductModel();
			product.setId(2);
			System.out.println("Testing deletion using DAO and product");
			productDao.remove(product);
			
			//reopening connection
			con = new MySQLConnectionFactory().connect();
			productDao.setCon(con);
			
			System.out.println("Testing deletion using DAO without product");
			productDao.remove(null);
			
		}catch (Exception e) {
			throw new SQLException ("Something went wrong.");
		}
		finally {
			con.close();
		}
	}
}
