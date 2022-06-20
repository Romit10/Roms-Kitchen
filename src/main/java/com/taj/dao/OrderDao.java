package com.taj.dao;

import java.util.List;

import com.taj.entity.Order;
import com.taj.model.ShoppingCart;

public interface OrderDao {

	public Order saveOrder(ShoppingCart cart);
	
	public List<Order> showOrdersForToday();
	
	public List<Order> allOrders();
	
	public List<Order> getOrderDetails(String userId);
	
	public Order getOrder(int orderId);
	
	public void confirm(int orderId);
	
	public void reject(int orderId);
	
	public void assign(String DId, int orderId);
	
	public void unassign(int orderId);
	
	public void saveCart(ShoppingCart cart,int pcode);
}
