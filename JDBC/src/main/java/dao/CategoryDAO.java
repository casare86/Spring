package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CategoryModel;
import model.ProductModel;

public class CategoryDAO extends GenericDao {
	
	public CategoryDAO(Connection connection) {
		super(connection);
	}
	
	public List<CategoryModel> getCategories() throws SQLException{
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM categories;";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.execute();
		
		ResultSet result = pstm.getResultSet();
		while(result.next()) {
			CategoryModel category = new CategoryModel(result.getInt(1), result.getString(2));
			list.add(category);
		}
		return list;
		
	}

	public List<CategoryModel> getCategoriesAdnProducts() throws SQLException {
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		String sql = "SELECT c.id as cat_id, c.name as cat_name, p.id as prod_id, p.name as prod_name, p.description FROM categories c" +
					" INNER JOIN products p ON c.id = p.category_id";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.execute();
		
		CategoryModel previous = null; 
		ResultSet result = pstm.getResultSet();
		while(result.next()) {
			if(previous == null || !previous.getName().equals(result.getString("cat_name"))) {
				CategoryModel category = new CategoryModel(result.getInt("cat_id"), result.getString("cat_name"));
				previous = category;
				list.add(category);
			}
			ProductModel product = new ProductModel(result.getInt(3), result.getString(4), result.getString(5)); 
			previous.addProduct(product);
			
		}
		return list;
	}

}
