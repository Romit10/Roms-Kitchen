package com.taj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.taj.dao.OrderDao;
import com.taj.dao.ProductDao;
import com.taj.dao.UserDao;
import com.taj.entity.Category;
import com.taj.entity.Order;
import com.taj.entity.Product;
import com.taj.model.CustomerInfo;
import com.taj.model.DeliveryInfo;
import com.taj.model.HotnessInfo;
import com.taj.model.ItemInfo;
import com.taj.model.Login;
import com.taj.model.ProductInfo;
import com.taj.model.ShoppingCart;
import com.taj.utils.TajUtils;
import com.taj.validator.CustomerInfoValidator;
import com.taj.validator.HotnessValidator;

@Controller
class HomepageController {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	UserDao userDao;
	
	CustomerInfo user=null;

	public CustomerInfo getUser() {
		return user;
	}

	public void setUser(CustomerInfo user) {
		this.user = user;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	private HotnessValidator hotnessValidator;

	public HotnessValidator getHotnessValidator() {
		return hotnessValidator;
	}

	public void setHotnessValidator(HotnessValidator hotnessValidator) {
		this.hotnessValidator = hotnessValidator;
	}
	
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerInfoValidator customerInfoValidator;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public CustomerInfoValidator getCustomerInfoValidator() {
		return customerInfoValidator;
	}

	public void setCustomerInfoValidator(CustomerInfoValidator customerInfoValidator) {
		this.customerInfoValidator = customerInfoValidator;
	}



	


	@RequestMapping(value = { "/", "/index", "/welcome" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {

		model.addAttribute("message", "Taj Restaurant");
		model.addAttribute("user", getUser());

		List<Category> categories = this.productDao.getCategories();

		model.addAttribute("leftCategories", categories.subList(0, categories.size() / 2));

		model.addAttribute("rightCategories", categories.subList(categories.size() / 2 + 1, categories.size()));

		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest httpServletRequest, Model map)
	{
		map.addAttribute("user1",new CustomerInfo());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("user1") CustomerInfo customerInfo,Model m) {
	String s=userDao.validate(customerInfo);
	if(s=="okay")
	{
	userDao.register(customerInfo);
	return "redirect:login";
	}
	else
	{
		m.addAttribute("message",s);
		return "register";
	}
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String addProduct(HttpServletRequest httpServletRequest, Model map)
	{
		map.addAttribute("user", getUser());
		map.addAttribute("product",new ProductInfo());
		return "addProduct";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductProcess(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("product") ProductInfo product,Model m) {
		String s=userDao.validateP(product);
		if(s=="okay")
		{
			productDao.register(product);
			return "redirect:index";
		}
		else
		{
			m.addAttribute("message",s);
			return "addProduct";
		}
	}
	
	@RequestMapping(value = "/addDelivery", method = RequestMethod.GET)
	public String addDelivery(HttpServletRequest httpServletRequest, Model map)
	{
		map.addAttribute("user", getUser());
		map.addAttribute("delivery",new DeliveryInfo());
		return "addDelivery";
	}
	
	@RequestMapping(value = "/addDelivery", method = RequestMethod.POST)
	public String addDeliveryProcess(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("delivery") DeliveryInfo delivery,Model m) {
		String s=userDao.validateD(delivery);
		if(s=="okay")
		{
			productDao.register1(delivery);
			return "redirect:index";
		}
		else
		{
			m.addAttribute("message",s);
			return "addDelivery";
		}
	}
	
	@RequestMapping(value = "/assignD", method = RequestMethod.POST)
	public String assignD(HttpServletRequest request, HttpServletResponse response,
	@RequestParam("DId") String DId , @RequestParam(value = "v", required = true) int orderId) {
	orderDao.assign(DId,orderId);
	return "redirect:/allOrders";
	}
	
	@RequestMapping(value = "/unassignD", method = RequestMethod.GET)
	public String unassignD(HttpServletRequest request, @RequestParam(value = "v", required = true) int orderId) {
	orderDao.unassign(orderId);
	return "redirect:/allOrders";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView mav = new ModelAndView("login");
	mav.addObject("login", new Login());
	return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("login") Login login,Model m) {
	CustomerInfo user1 = userDao.validateUser(login);
	if (user1 != null) {
		setUser(user1);
		return "redirect:index";
	} else {
		m.addAttribute("message", "Invalid userId or password");
		return "login";
	}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest httpServletRequest, Model map)
	{
		setUser(null);
		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);
		cart.removeAll();
		return "redirect:index";
	}
	

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(HttpServletRequest httpServletRequest) {

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);

		cart.setCustomerInfo(getUser());

		return "redirect:/orderConfirmation";

	}
	
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(@RequestParam(value = "productId", required = true) int productId,
			HttpServletRequest httpServletRequest) {

		Product product = this.productDao.getProductDetails(productId);

		if (product == null) {
			return "redirect:/403";
		}

		ProductInfo productInfo = new ProductInfo(product);

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);
		cart.addItemToCart(productInfo, 1);
		cart.setCustomerInfo(user);
		orderDao.saveCart(cart,productId);
		return "redirect:/shoppingCart";
	}

	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	public String shoppingCart(HttpServletRequest httpServletRequest, ModelMap map) {
		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);
		map.addAttribute("user", getUser());
		map.addAttribute("cart", cart);
		return "cart";
	}

	public String updateCart(HttpServletRequest httpServletRequest, ModelMap map) {
		return "";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "code", required = true) String code,
			HttpServletRequest httpServletRequest) {

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);
		cart.removeItemFromCart(code);
		orderDao.saveCart(cart, Integer.parseInt(code));
		return "redirect:/shoppingCart";
	}

	@RequestMapping(value = "/hotness", method = RequestMethod.GET)
	public String hotnessLevel(HttpServletRequest httpServletRequest, Model map) {

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);

		if (cart.isEmpty()) {
			return "redirect:shoppingCart";
		}

		HotnessInfo hotnessInfo = new HotnessInfo();

		List<ItemInfo> infos = new ArrayList<ItemInfo>();

		for (ItemInfo info : cart.getCartItem()) {
			if (TajUtils.categoriesForHotnessLevel().contains(info.getProductInfo().getCategoryId())) {
				infos.add(info);
			}
		}

		if (infos.isEmpty()) {
			return "redirect:checkout";
		}

		map.addAttribute("orderItems", infos);
		map.addAttribute("hotnessInfo", hotnessInfo);
		map.addAttribute("user", getUser());
		return "hotness";
	}

	@RequestMapping(value = "/hotness", method = RequestMethod.POST)
	public String hotnessLevel(HttpServletRequest httpServletRequest, Model map,
			@ModelAttribute("hotnessInfo") HotnessInfo hotnessInfo, BindingResult bindingResult) {

		hotnessValidator.validate(hotnessInfo, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:hotness";
		}

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);

		if (cart.isEmpty()) {
			return "redirect:shoppingCart";
		}

		cart.updateHotnessLevel(hotnessInfo.getHotnessLevel());

		return "redirect:checkout";
	}

	
	@RequestMapping(value = "/orderConfirmation", method = RequestMethod.GET)
	public String orderConfirmation(HttpServletRequest httpServletRequest, ModelMap map) {

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);
		if (cart.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		map.addAttribute("user", getUser());
		map.addAttribute("cart", cart);

		return "orderConfirmation";

	}

	@RequestMapping(value = "placeOrder", method = RequestMethod.GET)
	public String placeOrder(HttpServletRequest httpServletRequest, Model model) {

		ShoppingCart cart = TajUtils.getOrderFromSession(httpServletRequest);

		if (cart.isEmpty()) {
			return "redirect:/shoppingCart";
		}

		Order order=this.orderDao.saveOrder(cart);
		
		for( ItemInfo i : cart.getCartItem())
		{
		orderDao.saveCart(cart, Integer.parseInt(i.getProductInfo().getProductCode()));
		}
		
		TajUtils.removeOrderFromSession(httpServletRequest);
		model.addAttribute("user", getUser());
		model.addAttribute("order", order);

		return "orderPlaced";
	}
	
	@RequestMapping(value = "/allOrders", method = RequestMethod.GET)
	public String allOrders(HttpServletRequest httpServletRequest, Model model) {

		List<Order> list = this.orderDao.allOrders();
		model.addAttribute("orders", list);
		model.addAttribute("user", getUser());

		return "orderList";
	}
	

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String findOrder(@RequestParam(value = "v", required = true) String v, Model map) {

		if (!v.equalsIgnoreCase("today")) {
			map.addAttribute("error", "Bad Input");
		} else {

			List<Order> list = this.orderDao.showOrdersForToday();
			map.addAttribute("orders", list);

		}
		map.addAttribute("user", getUser());
		return "orderList";
	}

	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public String getOrderDetails(Model map) {

		
		map.addAttribute("user", getUser());
		List<Order> orders= this.orderDao.getOrderDetails(getUser().getUserId());
		map.addAttribute("orders", orders );
		return "orderDetails";

	}
	
	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	public String getOrderDetails(@RequestParam(value = "o", required = true) String id, Model map) {

		
		
		map.addAttribute("order", this.orderDao.getOrder(Integer.parseInt(id)));
		map.addAttribute("user", getUser());
		return "getOrder";

	}
	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public String confirmOrder(@RequestParam(value = "v", required = true) int orderId, Model map) {

		this.orderDao.confirm(orderId);
		map.addAttribute("user", getUser());
		return "redirect:/allOrders";

	}
	
	@RequestMapping(value = "/rejectOrder", method = RequestMethod.GET)
	public String rejectOrder(@RequestParam(value = "v", required = true) int orderId, Model map) {

		this.orderDao.reject(orderId);
		map.addAttribute("user", getUser());
		return "redirect:/allOrders";

	}
	
	
}
