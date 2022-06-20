package com.taj.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.taj.dao.ProductDao;
import com.taj.entity.Category;
import com.taj.entity.Product;
import com.taj.model.CustomerInfo;
import com.taj.model.DeliveryInfo;
import com.taj.model.ProductInfo;

public class ProductDaoImpl implements ProductDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	public void register(ProductInfo product) {
	String sql = "insert into product values(?,?,?,?,?)";
	jdbcTemplate.update(sql, new Object[] { product.getProductCode(), product.getProductName(), product.getPrice(),
	product.getCategoryId(), product.getDescription() });
	}
	
	public void register1(DeliveryInfo delivery) {
		String sql = "insert into delivery values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { delivery.getDId(), delivery.getfName(), delivery.getlName(),
		delivery.getEmailId(), delivery.getPhone() , delivery.getVehicleNo() , delivery.getVehicleName() });
		}

	public Product getProductDetails(int productId) {

		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		String query = "from Product where productId=:productId";
		Query query2 = session.createQuery(query);
		query2.setParameter("productId", productId);

		Object object = query2.uniqueResult();

		transaction.commit();
		session.close();

		return (Product) object;
	}

	public List<Category> getCategories() {

		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		String query = "from Category";
		Query query2 = session.createQuery(query);

		Object object = query2.list();

		List<Category> list = (List<Category>) object;

		transaction.commit();
		session.close();
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
