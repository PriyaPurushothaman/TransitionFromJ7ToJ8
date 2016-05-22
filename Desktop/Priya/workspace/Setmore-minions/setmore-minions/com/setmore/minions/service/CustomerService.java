package com.setmore.minions.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.setmore.minions.jdo.AdminJdo;
import com.setmore.minions.jdo.CustomerJdo;
import com.setmore.minions.jdo.PMF;

public class CustomerService {
	private static final Logger log = Logger.getLogger(CustomerService.class.getName());

	public String saveCustomersData(String username, String email, String CustomerName, String CustomerEmail,
			String CustomerMobile, String CustomerAddress, String CustomerZip) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CustomerJdo customerData = new CustomerJdo();

		try {
			log.info("Inside the saveCustomer");

			System.out.println("username:" + username + "email:" + email);

			customerData.setUseremail(email);
			customerData.setUsername(username);
			customerData.setCustomername(CustomerName);
			customerData.setCustomeremail(CustomerEmail);
			customerData.setAddress(CustomerAddress);
			customerData.setMobile(CustomerMobile);
			customerData.setZip(CustomerZip);
			pm.makePersistent(customerData);
			
			log.info("Stored the customer data into the DB");
		} catch (Exception e) {
			log.info("Exception while store the customer info" + e);
		} finally {
			pm.close();
		}
		Gson gson = new Gson();
	       
		String jsonData = gson.toJson(customerData);
		
		
		System.out.println("JSON Value   "+jsonData);
	    return jsonData;

	}

	public List<CustomerJdo> getCustomerData(String email) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("inside getCustomerData fnction"+email);
		Query q = pm.newQuery(CustomerJdo.class);
		q.setFilter("useremail == useremailParameter");
		q.declareParameters("String useremailParameter");
		@SuppressWarnings("unchecked")
		List<CustomerJdo> customeData = (List<CustomerJdo>) q.execute(email);

		return customeData;
	}

	public void send(String cusEmail,String autoPass) {
		// TODO Auto-generated method stub
		String customerName = "customer name";
		log.info("email" +cusEmail);
		 String subject = "Hello" + customerName;
		 
	        // message contains HTML markups
			String msgBody = "Dear " + customerName + " \n\nyour account has been created in SetMore "+ "\n\n Username " +cusEmail + "\n\n Password " +autoPass ; 

		
	 
		//String msgBody = "Dear " + name + " your account has been created successfully"+ "/n"+ "This is your password" +autoPass ; 
		Properties p=new Properties();
		p.put("mail.smtp.auth","true"); //authentication purpose 
		p.put("mail.smtp.host","smtp.gmail.com"); //only gmail accout is possible
		p.put("mail.smtp.port",587); //default port id for smtp
		p.put("mail.smtp.starttls.enable","true"); 
		Session session = Session.getDefaultInstance(p,new javax.mail.Authenticator() 
		{
		protected PasswordAuthentication getPasswordAuthentication()
		{
		return new PasswordAuthentication("priya.purushoth@a-cti.com","");
		}
		});
		try 
		{
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("priya.purushoth@a-cti.com","SetMore-Minions"));
		msg.addRecipient(Message.RecipientType.TO,new InternetAddress(cusEmail));
		msg.setSubject(subject);
		msg.setText(msgBody);
		Transport.send(msg); //sends the msg using send() in Transport class
		System.out.println("Sent");
		} 
		catch (IOException e)
		{
		System.out.println(e);
		} 
		catch (MessagingException e)
		{
		System.out.println(e);
		}
		 
		
	}

	public List<CustomerJdo> loadCustomerOfThis(Long customer_Id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.get().getPersistenceManager();

		log.info("inside loadCustomerOfThis fnction"+customer_Id);
		Query q = pm.newQuery(CustomerJdo.class);
		q.setFilter("id == idParameter");
		q.declareParameters("Long idParameter");
		@SuppressWarnings("unchecked")
		List<CustomerJdo> customeData = (List<CustomerJdo>) q.execute(customer_Id);

		return customeData;
	}

}
