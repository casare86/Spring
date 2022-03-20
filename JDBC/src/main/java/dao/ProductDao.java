package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.MySQLConnectionFactory;
import model.CategoryModel;
import model.ProductModel;

public class ProductDao extends GenericDao {
	
	public ProductDao(Connection connection) {
		super(connection);
	}

	//no prevention against SQL Injection (DO NOT USE IT)
	public void insert(ProductModel product) throws SQLException {
		try {
			
			String sql = "INSERT INTO products (name, description) "
					+ "VALUES ('" + product.getName() + "', '" + product.getDescription() + "')";
			Statement stm = con.createStatement();
			stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
			
			//get the ID of the inserted Item
			ResultSet result = stm.getGeneratedKeys();
			while(result.next()) {
				Integer id = result.getInt(1);
				System.out.println("Product succesfully inserted.\nID number: " + id);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			con.close();
			System.out.println("Insert Connection Closed");
		}
	}
	
	//prepare statement prevents SQL Injection since it takes precaautions to use every input part of the same string no matter what
	public void prepareInsert(ProductModel product) throws SQLException {
		try 
		{
			String sql = "INSERT INTO products (name, description) VALUES (?,?)";
			
			PreparedStatement stm = con.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, product.getName());
			stm.setString(2, product.getDescription());
			stm.execute();
			
			addItem("SmartTV", "45 inches", stm); //another way to add itens to sql statement
			
			List<ProductModel> productList = new ArrayList<ProductModel>();
			ProductModel prod1 = new ProductModel("SmartPhone", "Iphone X");
			ProductModel prod2 = new ProductModel("SmartPhone", "Iphone 13"); 
			productList.add(prod1);
			productList.add(prod2);
			addListItem(productList, stm);
			
			//get the ID of the inserted Item
			ResultSet result = stm.getGeneratedKeys();
			while(result.next()) {
				Integer id = result.getInt(1);
				System.out.println("Product succesfully inserted.\nID number: " + id);
			}
			result.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			con.close();
			System.out.println("Insert Connection Closed");
		}
	}
	
	private void addListItem(List<ProductModel> productList, PreparedStatement stm) throws SQLException {
		
		this.con.setAutoCommit(false);//disables autocomit to ensure whole transaction for list insertion
		try
		{
			for (ProductModel product : productList) {
				addItem(product.getName(), product.getDescription(), stm);
			}
			this.con.commit(); //the commit needs to be done manually now
		}
		catch (Exception e) {
			System.out.println("Applying rollback!");
			this.con.rollback();
			throw new SQLException (e.getMessage());
		}
	}

	public static void addItem(String arg1, String arg2, PreparedStatement stm) throws SQLException {
		stm.setString(1, arg1);
		stm.setString(2, arg2);
		stm.execute();
	}
	
	public void remove(ProductModel product) throws SQLException {
		try 
		{
			String sql = "DELETE FROM products WHERE id > ?";
			Integer value = 2;
			if (product != null) {
				sql = "DELETE FROM products WHERE id = ?";
				value = product.getId();
			}
			
			PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stm.setInt(1, value);
			stm.execute();
			
			Integer rows = stm.getUpdateCount();
			System.out.println(rows + " rows deleted.");
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			con.close();
			System.out.println("Remove Connection Closed");
		}
	}
	
	public List<ProductModel> getProducts() throws SQLException{
		List<ProductModel> list = new ArrayList<ProductModel>();
		
		String sql = "SELECT * FROM products;";
		
		try{
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.execute();
			
			ResultSet rst = pstm.getResultSet();
			while(rst.next()) {
				ProductModel prod = new ProductModel (rst.getInt(1), rst.getString(2), rst.getString(3));
				list.add(prod);
			}
			rst.close();
		}
		catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		finally {
			con.close();
		}
		return list;
	}

	public List<ProductModel> getProductsByCategory(CategoryModel cat) throws SQLException {
		
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "SELECT * FROM products WHERE category_id = ?;";
		try{
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, cat.getId());
			pstm.execute();
			
			ResultSet rst = pstm.getResultSet();
			while(rst.next()) {
				ProductModel prod = new ProductModel (rst.getInt(1), rst.getString(2), rst.getString(3));
				list.add(prod);
			}
			rst.close();
		}
		catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		return list;
	}
}
