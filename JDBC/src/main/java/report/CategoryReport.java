package report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connections.MySQLConnectionFactory;
import dao.CategoryDAO;
import dao.ProductDao;
import model.CategoryModel;
import model.ProductModel;

public class CategoryReport {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = new MySQLConnectionFactory().connect();
		CategoryDAO categoryDAO = new CategoryDAO(connection);
		List<CategoryModel> categories = categoryDAO.getCategoriesAdnProducts();
		categories.stream().forEach(cat -> {
			System.out.println("\nProducts in category: " + cat.getName());
			for (ProductModel product : cat.getProducts()) {
				System.out.println(product);
			}
		});
	}

}
