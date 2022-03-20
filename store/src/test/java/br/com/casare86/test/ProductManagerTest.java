package br.com.casare86.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.casare86.store.DTO.SalesReportVO;
import br.com.casare86.store.dao.CategoryDao;
import br.com.casare86.store.dao.ClientDao;
import br.com.casare86.store.dao.OrderDao;
import br.com.casare86.store.dao.OrderEntryDao;
import br.com.casare86.store.dao.ProductDao;
import br.com.casare86.store.model.CategoryModel;
import br.com.casare86.store.model.ClientModel;
import br.com.casare86.store.model.CompositeKeyId;
import br.com.casare86.store.model.CompositeKeyModel;
import br.com.casare86.store.model.OrderEntryModel;
import br.com.casare86.store.model.OrderModel;
import br.com.casare86.store.model.ProductModel;
import br.com.casare86.store.util.JPAUtil;

public class ProductManagerTest {
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		ClientDao clientDao = new ClientDao(em);
		OrderDao orderDao = new OrderDao(em);
		OrderEntryDao entryDao = new OrderEntryDao(em);
		
		//createProductTest();
		//recoverProducts();
		
		//ClientModel clientX = new ClientModel("Name", "cpf");
		//createClient(clientX);

		//OrderModel orderX = new OrderModel(clientX);
		//createOrder(orderX);
		//placeOrder(1, 1, 1 );
		
		//System.out.println(orderDao.getTotalIncome());
		
		//List<SalesReportVO> salesReport = orderDao.getSalesReport();
		//salesReport.forEach(System.out::println);
		
		//OrderModel order = em.find(OrderModel.class, 1l);
		//orderDao.getOrderWithClient(1l);
		
		//API Criteria Search
		//productDao.searchByParameters("SmartPhone", null, null);
		
		//Using Find in Tables with composite PK
		em.find(CompositeKeyModel.class, new CompositeKeyId('A', "XPTO"));
		
		
		
	}

	private static void placeOrder(Long client, Long product, Integer quantity) {
		System.out.println("\nOrder creation start:");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		ClientDao clientDao = new ClientDao(em);
		OrderDao orderDao = new OrderDao(em);
		OrderEntryDao entryDao = new OrderEntryDao(em);
		
		ClientModel client1 = clientDao.getClientById(client);
		ProductModel product1 = productDao.getProductById(product);
		OrderModel order = new OrderModel(client1);
		OrderEntryModel entry1 = new OrderEntryModel(quantity, order, product1);
		
		em.getTransaction().begin();
		
		order.addEntry(entry1);
		orderDao.addOrder(order);
		entryDao.addEntry(entry1);

		em.getTransaction().commit();
		System.out.println("\nNew order placed.\n");
	}
	
	private static void createClient(ClientModel client) {
		EntityManager em = JPAUtil.getEntityManager();
		ClientDao clientDao = new ClientDao(em);
		
		em.getTransaction().begin();
		clientDao.addClient(client);
		em.getTransaction().commit();
		System.out.println(String.format("New client created: %d - %s", client.getId(), client.getName()));
	}
	
	private static void createOrder(OrderModel order) {
		EntityManager em = JPAUtil.getEntityManager();
		OrderDao orderDao = new OrderDao(em);
		
		em.getTransaction().begin();
		orderDao.addOrder(order);
		em.getTransaction().commit();
	}
	
	private static void recoverProducts() {
		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		
		ProductModel product = productDao.getProductById(1l);
		System.out.println("Recovered product: " + product);
		System.out.println();
		
		List<ProductModel> productList = productDao.getAll();
		productList.forEach(p -> System.out.println(p));
		
		System.out.println("\nGet Product by Name:");
		productList = productDao.getByName("Xiaiomi Redmi");
		productList.forEach(p -> System.out.println(p));
		
		System.out.println("\nGet Product by Category (Name)");
		String categoryName = "Decoration";
		productList = productDao.getByCategory(categoryName);
		productList.forEach(p -> System.out.println(p + " Category: " + categoryName));
		
		System.out.println("Product price: ");
		Long id = 48l;
		product = productDao.getProductById(id);
		BigDecimal productPrice = productDao.recoverPriceById(id);
		System.out.println(product + " recovered price: " + productPrice);
	}

	private static void createProductTest() {
		CategoryModel category = new CategoryModel("Test");
		ProductModel cel = new ProductModel("Xiaiomi Redmi", "New Xiaiomi cellphone", new BigDecimal("799.90"), category);
		
		//EntityManagerFactory needs the factory passing the persistence-unit name to create the connections and its needs
		EntityManager em = JPAUtil.getEntityManager();
		CategoryDao categoryDao = new CategoryDao(em);
		ProductDao productDao = new ProductDao(em);
		
		em.getTransaction().begin();
		
		categoryDao.addCategory(category);
		productDao.addProduct(cel);//saves the object accordingly to its mapped entity
		System.out.println("Product added");
		
		em.flush(); //update DB but don´t finish the transaction
		em.clear(); //discard the managed status from entities monitored by JPA
		
		//merge returns a reference to the object, but the entity parameter keep it´s "detached" state
		
		category = em.merge(category); //recovers entity to an "Managed" state 
		category.setName("Trial");
		
		//delete items from database
		em.remove(cel);
		
		//close the tansaction
		em.getTransaction().commit();
		em.close();
		
		System.out.println("Category removed");
	}
}
