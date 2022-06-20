package com.taj.dao;

import org.springframework.stereotype.Component;

import com.taj.model.CustomerInfo;
import com.taj.model.DeliveryInfo;
import com.taj.model.Login;
import com.taj.model.ProductInfo;

@Component
public interface UserDao {

	void register(CustomerInfo user);
	CustomerInfo validateUser(Login login);
	String validate(CustomerInfo user);
	String validateD(DeliveryInfo delivery);
	String validateP(ProductInfo pro);
}
