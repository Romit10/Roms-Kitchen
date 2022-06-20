package com.taj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.taj.dao.UserDao;
import com.taj.model.CustomerInfo;
import com.taj.model.DeliveryInfo;
import com.taj.model.Login;
import com.taj.model.ProductInfo;

@Service
public class UserDaoImpl implements UserDao {
		
		@Autowired
		DataSource datasource;
		@Autowired
		JdbcTemplate jdbcTemplate;
		public String validateP(ProductInfo pro)
		{
			String sql = "select * from product where product_id='" + Integer.parseInt(pro.getProductCode())+"'";
			List<ProductInfo> users = jdbcTemplate.query(sql, new ProMapper());
			if(users.size()>0)
				return "ProductId exists already";
			return "okay";
		}
		public String validateD(DeliveryInfo user)
		{
			String sql = "select * from delivery where DId='" + user.getDId()+"'";
			List<DeliveryInfo> users = jdbcTemplate.query(sql, new DelMapper());
			if(users.size()>0)
				return "UserId exists already";
			if(user.getPhone().length()!=10)
				return "Phone Number not Valid";
			String sql1 = "select * from user where email_id='" + user.getEmailId()+"'";
			List<DeliveryInfo> users1 = jdbcTemplate.query(sql1, new DelMapper());
			if(users1.size()>0)
				return "EmailId exists already";
			EmailValidator emailValidator = EmailValidator.getInstance();
			if(!emailValidator.isValid(user.getEmailId()))
				return "EmailId not Valid";
			return "okay";
		}
		public String validate(CustomerInfo user)
		{
			String sql = "select * from user where user_id='" + user.getUserId()+"'";
			List<CustomerInfo> users = jdbcTemplate.query(sql, new UserMapper());
			if(users.size()>0)
				return "UserId exists already";
			if(user.getPhone().length()!=10)
				return "Phone Number not Valid";
			String sql1 = "select * from user where email_id='" + user.getEmail()+"'";
			List<CustomerInfo> users1 = jdbcTemplate.query(sql1, new UserMapper());
			if(users1.size()>0)
				return "EmailId exists already";
			EmailValidator emailValidator = EmailValidator.getInstance();
			if(!emailValidator.isValid(user.getEmail()))
				return "EmailId not Valid";
			return "okay";
		}
		
		public void register(CustomerInfo user) {
		String sql = "insert into user values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { user.getUserId(), user.getfName(), user.getlName(),
		user.getEmail(), user.getPhone(), user.getAddress(), user.getPassword() });
		}
		public CustomerInfo validateUser(Login login) {
		String sql = "select * from user where user_id='" + login.getUserId() + "' and password='" + login.getPassword()
		+ "'";
		List<CustomerInfo> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
		}
		}

		class UserMapper implements RowMapper<CustomerInfo> {
		public CustomerInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		CustomerInfo user = new CustomerInfo();
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setfName(rs.getString("fname"));
		user.setlName(rs.getString("lname"));
		user.setEmail(rs.getString("email_id"));
		user.setAddress(rs.getString("address"));
		user.setPhone(rs.getString("phone"));
		return user;
		}
}
		class DelMapper implements RowMapper<DeliveryInfo> {
			public DeliveryInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			DeliveryInfo user = new DeliveryInfo();
			user.setDId(rs.getString("DId"));
			user.setfName(rs.getString("fName"));
			user.setlName(rs.getString("lName"));
			user.setEmailId(rs.getString("emailId"));
			user.setVehicleNo(rs.getString("vehicleNo"));
			user.setPhone(rs.getString("phone"));
			user.setVehicleName(rs.getString("vehicleName"));
			return user;
			}
	}
		
		class ProMapper implements RowMapper<ProductInfo> {
			public ProductInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			ProductInfo pro= new ProductInfo();
			pro.setProductCode(rs.getString("product_id"));
			pro.setProductName(rs.getString("name"));
			pro.setPrice(rs.getDouble("price"));
			pro.setDescription(rs.getString("description"));
			pro.setCategoryId(rs.getInt("category_id"));
			
			return pro;
			}
	}
