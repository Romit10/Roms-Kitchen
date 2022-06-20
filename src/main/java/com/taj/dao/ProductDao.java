package com.taj.dao;

import java.util.List;

import com.taj.entity.Category;
import com.taj.entity.Product;
import com.taj.model.CustomerInfo;
import com.taj.model.DeliveryInfo;
import com.taj.model.ProductInfo;

public interface ProductDao {
	
	public void register(ProductInfo product);

	public Product getProductDetails(int itemId);
	
	public List<Category> getCategories();
	
	public void register1(DeliveryInfo delivery);
	
}
