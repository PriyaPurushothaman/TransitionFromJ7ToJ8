package com.setmore.minions.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.setmore.minions.jdo.AdminJdo;
import com.setmore.minions.jdo.CustomerJdo;
import com.setmore.minions.jdo.PMF;
import com.setmore.minions.service.AdminService;
import com.setmore.minions.service.CustomerService;

@Controller
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class.getName());

	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 6;

	
	
	
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("login");
	}

	
	
	
	
	
	@RequestMapping("/clients")
	public ModelAndView clients() {
		return new ModelAndView("clients");
	}

	
	
	
	
	@RequestMapping(value = "/{url}", method = RequestMethod.GET)
	public String error404(@PathVariable String url) {
		System.out.println("custom error handler");
		return "login";
	}

	
	
	
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelobj, HttpServletRequest request) {
		HttpSession session = request.getSession();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		log.info("inside signin: " + "email" + email + "password" + password);
		AdminService adminService = new AdminService();
		List<AdminJdo> checkLoginData = adminService.getLoginDataFromDB(email, password);
		log.info("checkLoginData" + checkLoginData);
		if (!checkLoginData.isEmpty()) {
			for (AdminJdo adminName : checkLoginData) {
				log.info("admin name " + adminName.getUsername());
				String Username = adminName.getUsername();
				long AdminId = adminName.getId();
				session.setAttribute("AdminId", AdminId);
				session.setAttribute("AdminName", Username);
				session.setAttribute("AdminEmail", email);
			}
			log.info("Login success");
			CustomerService cusServiceObj = new CustomerService();
			List<CustomerJdo> customersList = cusServiceObj.getCustomerData(email);
			request.setAttribute("AdminEmail", email);
			//session.setAttribute("customersList", customersList);
			 modelobj.addAttribute("customersList", customersList);

			if (customersList.equals(null)) {
				log.info("empty customer data");
				System.out.println("empty");
			} else {
				log.info("customer data loading");
				 modelobj.addAttribute("customersList", customersList);
					return "clients";

			}
			return "clients";
		}
		log.info("Login failure");
		String errorMsg = "Invalid username and password";
		modelobj.addAttribute("errorMsg", errorMsg);
		return "login";
	}



	 
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	 public String signup(@RequestParam("username") String username,
	 @RequestParam("email") String email,
	 @RequestParam("password") String password, ModelMap
	 modelobj,HttpServletRequest request,
	 HttpServletResponse response) {
	 log.info("inside signup: " + "email" + email + "password" + password +
	 "username" + username);
	 PersistenceManager pm = PMF.get().getPersistenceManager();
	 AdminService adminService = new AdminService();
	 HttpSession session = request.getSession();
	
	 List<AdminJdo> checkEmailExist = adminService.checkEmailExistInDb(email);
	
	 if (checkEmailExist.isEmpty()) {
	 AdminJdo storeAdminData = new AdminJdo();
	 CustomerService cusService = new CustomerService();
	
	 try {
	 storeAdminData.setEmail(email);
	 storeAdminData.setUsername(username);
	 storeAdminData.setPassword(password);
	 pm.makePersistent(storeAdminData);
	 long AdminId = storeAdminData.getId();
	 System.out.println(AdminId);
	 System.out.println(email);
	 System.out.println(username);
	 session.setAttribute("AdminId", AdminId);
	 session.setAttribute("AdminName", username);
	 session.setAttribute("AdminEmail", email);
	 request.setAttribute("AdminEmail", email);
	 log.info("Registered successfully");
	 CustomerService cusServiceObj = new CustomerService();
	 cusServiceObj.getCustomerData(email);
	 } catch (Exception e) {
	
	 log.info("exception while storing" + e);
	 } finally {
	
	 }
	 } else {
	 log.info("Email Exist");
	 String errorMsg = "This Email already Exist";
	 modelobj.addAttribute("errorMsg", errorMsg);
	 return "login";
	
	 }
	
	 modelobj.addAttribute("username", username);
	 modelobj.addAttribute("email", email);
	
	 return "clients";
	 }
	
	
	
	
	
	
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public  String addCustomer(@RequestBody String customerData,HttpServletRequest req, ModelMap model )
		{
		 System.out.println("begin here");
			PersistenceManager pm = PMF.get().getPersistenceManager();

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			try {
				jsonObject = (JSONObject) parser.parse(customerData);
			}
			catch(Exception e)
			{
				e.printStackTrace();

			}
			
			
			String adminName = (String) jsonObject.get("adminName");
			String adminEmail = (String) jsonObject.get("adminEmail");
			String customerName = (String) jsonObject.get("customerName");
			String customerEmail = (String) jsonObject.get("customerEmail");
			String customerMobile = (String) jsonObject.get("customerMobile");
			String customerZip = (String) jsonObject.get("customerZip");
			String customerAddress = (String) jsonObject.get("customerAddress");

		CustomerService cusService = new CustomerService();
	String jsonData = 	cusService.saveCustomersData(adminName, adminEmail, customerName, customerEmail,
				customerMobile, customerAddress,customerZip);
	System.out.println("JSON Value   "+jsonData);

		return jsonData;
	}


	
	
	
	
	
	
	@RequestMapping(value = "/getCustomerList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public  String getCustomerList(@RequestBody String email,HttpServletRequest req, ModelMap model )
	 {
	 	System.out.println("inside getCustomerList");
	 	PersistenceManager pm = PMF.get().getPersistenceManager();

	 	JSONParser parser = new JSONParser();
	 	JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(email);
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		String adminEmail = (String) jsonObject.get("adminEmail");
	 	System.out.println("admin email " +adminEmail);


		CustomerService cusService = new CustomerService();

	 	List<CustomerJdo> customerList = cusService.getCustomerData(adminEmail); 

	 	
	 		Gson gson = new GsonBuilder().create();
	 	
	    
	 	String jsonData = gson.toJson(customerList);
	 	System.out.println("gettot ==="+jsonData);
	 		
	     return jsonData;

	 }
	
	@RequestMapping(value = "/loadCutomerDataFroDB", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public  String loadCutomerDataFroDB(@RequestBody String customerId,HttpServletRequest req, ModelMap model )
	 {
	 	System.out.println("inside loadCutomerDataFroDB");
	 	PersistenceManager pm = PMF.get().getPersistenceManager();

	 	JSONParser parser = new JSONParser();
	 	JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(customerId);
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		Long customer_Id = (Long) jsonObject.get("customerId");
	 	System.out.println("customerId " +customer_Id);


//	 		String queryStr = "SELECT FROM " + CustomerJdo.class.getName() + " WHERE useremail=="+email;
//	 		Query q = pm.newQuery(queryStr);
//	 		List<CustomerJdo> customerList = (List<CustomerJdo>) q.execute();
		CustomerService cusService = new CustomerService();

	 	List<CustomerJdo> singleCustomerDetails = cusService.loadCustomerOfThis(customer_Id); 

	 	
	 		Gson gson = new GsonBuilder().create();
	 		 	String jsonData = gson.toJson(singleCustomerDetails);
	 	System.out.println("singleCustomerData ==="+jsonData);
	 		
	     return jsonData;

	 }

	 
	
	
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String clients(HttpServletRequest request, HttpServletResponse response) {
		return "clients";

	}


	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "login";
	}
	
	
	
	
	@RequestMapping(value = "/generatePassword", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public  String sendLogin(@RequestBody String customerEmail,HttpServletRequest req, ModelMap model )
	 {
		log.info("inside generate password");
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(customerEmail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String cusEmail = (String) jsonObject.get("customerEmail");
		log.info("customerEmail"+cusEmail);
		
		
		
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
		String cust_password = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			cust_password += letters.substring(index, index + 1);
		}

		log.info(cust_password);

		CustomerService cusService = new CustomerService();
		cusService.send(cusEmail,cust_password);
		
		
		String jsonData = "sent";
    	System.out.println("sent / Failed ==="+jsonData);
    	
        return jsonData;
	}

}