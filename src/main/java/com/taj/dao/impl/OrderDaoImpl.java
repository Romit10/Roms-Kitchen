package com.taj.dao.impl;


import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


import com.taj.dao.OrderDao;
import com.taj.entity.Cart;
import com.taj.entity.Delivery;
import com.taj.entity.Order;
import com.taj.entity.OrderItems;
import com.taj.entity.User;
import com.taj.model.ItemInfo;
import com.taj.model.ShoppingCart;

public class OrderDaoImpl implements OrderDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void saveCart(ShoppingCart cart, int pcode)
	{
		String userId=cart.getCustomerInfo().getUserId();
		String sql="from Cart where userId=:userId and productId=:productId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("productId", String.valueOf(pcode) );
		Cart c = (Cart) query.uniqueResult();
		session.close();
		if(c==null)
		{
		String sql1 = "insert into cart values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql1, new Object[] { userId , String.valueOf(pcode) , cart.getSubTotal(),
		cart.getTaxes(), cart.getTotalPrice() , cart.findItemInCart(String.valueOf(pcode)).getQuantity() , cart.getOrderNumber() });
		}
		else
		{
			String sql1 = "UPDATE Cart SET subTotal=:subTotal , taxes=:taxes , totalPrice=:totalPrice , quantity=:quantity , orderId=:orderId where userId=:userId and productId=:productId";
			Session session1 = this.sessionFactory.openSession();
			Query query1 = session1.createQuery(sql1);
			query1.setParameter("subTotal", cart.getSubTotal());
			query1.setParameter("taxes", cart.getTaxes());
			query1.setParameter("totalPrice", cart.getTotalPrice());
			query1.setParameter("quantity", cart.findItemInCart(String.valueOf(pcode)).getQuantity() );
			query1.setParameter("orderId", cart.getOrderNumber());
			query1.setParameter("userId", userId);
			query1.setParameter("productId", String.valueOf(pcode));
			query1.executeUpdate();
			session1.close();
		}
	}
	
	public void confirm(int orderId) {
		String sql = "UPDATE Order SET status='Confirmed' where orderId=:orderId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("orderId", orderId);
		
		query.executeUpdate();
		session.close();
	}
	
	public void reject(int orderId) {
		String sql = "UPDATE Order SET status='Rejected' where orderId=:orderId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("orderId", orderId);
		
		query.executeUpdate();
		session.close();
		}

	public Order saveOrder(ShoppingCart cart) {	

		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		User user = null;

		if (this.getUser(cart.getCustomerInfo().getEmail()) == null) {
			user = new User();
			user.setfName(cart.getCustomerInfo().getfName());
			user.setlName(cart.getCustomerInfo().getlName());
			user.setEmailId(cart.getCustomerInfo().getEmail());
			user.setPhone(cart.getCustomerInfo().getPhone());
			user.setAddress(cart.getCustomerInfo().getAddress());
			session.persist(user);			
		}else{
			user = this.getUser(cart.getCustomerInfo().getEmail());
		}
		
				
				
		Order order = new Order(user);
		order.setOrderId(this.getMaxOrderNum()+1);
		order.setPrice(cart.getTotalPrice());
		order.setDate(new Timestamp(System.currentTimeMillis()));
		
		session.persist(order);
		
		for (ItemInfo info : cart.getCartItem()) {
			OrderItems items = new OrderItems(order);
			items.setQuantity(info.getQuantity());
			items.setName(info.getProductInfo().getProductName());
			items.setHotnessLevel(info.getHotnessLevel());
			session.persist(items);
		}
		
		order.setStatus("Waiting");
		order.setDId(null);
		transaction.commit();
		session.close();
		
		cart.setOrderNumber(order.getOrderId());
		return order;
	}

	private User getUser(String emailId) {
		Session session = this.sessionFactory.openSession();
		String sql = "from User where emailId=:emailId";
		Query query = session.createQuery(sql);
		query.setParameter("emailId", emailId);
		Object val = (Object) query.uniqueResult();
		
		session.close();

		return (User) val;

	}

	private int getMaxOrderNum() {
		String sql = "Select max(o.orderId) from " + Order.class.getName() + " o ";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sql);
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		session.close();
		return value;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Order> showOrdersForToday() {
		
		String sql = "from Order where date>=:startDate and date<=:endDate";
		Date date = new Date(new Date().getTime()-24*3600*1000);
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setTimestamp("startDate", new Timestamp(date.getTime()));
		query.setTimestamp("endDate", new Timestamp(System.currentTimeMillis()));
		List<Order> orders = query.list();
		session.close();
		return orders;
		
	}
	
public List<Order> allOrders() {
		
		String sql = "from Order";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sql);
		List<Order> orders = query.list();
		session.close();
		return orders;
		
	}

	public List<Order> getOrderDetails(String userId){
		Session session1 = this.sessionFactory.openSession();
		String sql1 = "from User where userId=:userId";
		Query query1 = session1.createQuery(sql1);
		query1.setParameter("userId", userId);
		User user = (User) query1.uniqueResult();
		session1.close();
		
		String sql = "from Order where userId=:userId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("userId", user);
		
		List<Order> orders = query.list();
		session.close();
		return orders;
		
	}
	
	public Order getOrder(int orderId){
		String sql = "from Order where orderId=:orderId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("orderId", orderId);
		
		Order order = (Order) query.uniqueResult();
		session.close();
		return order;
		
	}
	
	public void assign(String DId,int orderId)
	{
		String sql="from Delivery where DId=:DId";
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(sql);
		query.setParameter("DId", DId);
		Delivery del = (Delivery) query.uniqueResult();
		session.close();
		
		String sql1 = "UPDATE Order SET DId=:DId where orderId=:orderId";
		Session session1 = this.sessionFactory.openSession();
		Query query1 = session1.createQuery(sql1);
		query1.setParameter("DId", del);
		query1.setParameter("orderId", orderId);
		
		query1.executeUpdate();
		session1.close();
	}
	
	public void unassign(int orderId)
	{
		String sql1 = "UPDATE Order SET DId=:DId where orderId=:orderId";
		Session session1 = this.sessionFactory.openSession();
		Query query1 = session1.createQuery(sql1);
		query1.setParameter("DId", null);
		query1.setParameter("orderId", orderId);
		
		query1.executeUpdate();
		session1.close();
	}

}
