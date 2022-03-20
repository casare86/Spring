package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connections.MySQLConnectionFactory;
import dao.ProductDao;
import model.ProductModel;

public class ProductList {
	
	public static void main(String[] args) throws SQLException {
		
		Connection con =  new MySQLConnectionFactory().connect();
		
		System.out.println("Try to insert a product");
		ProductModel product = new ProductModel();
		product.setName("Trials 2");
		product.setDescription("Testing insertion usin DAO 2.1");
		
		ProductDao productDao = new ProductDao(con);
		productDao.prepareInsert(product);
		
		con = new MySQLConnectionFactory().connect();
		System.out.println("List All Products:");
		List<ProductModel> products = productDao.getProducts();
		products.stream().forEach(prod -> System.out.println(prod));
	}

}
