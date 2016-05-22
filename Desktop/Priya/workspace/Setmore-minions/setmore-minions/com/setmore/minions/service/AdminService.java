package com.setmore.minions.service;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.util.logging.Logger;

import com.setmore.minions.controller.AdminController;
import com.setmore.minions.jdo.AdminJdo;
import com.setmore.minions.jdo.PMF;

public class AdminService {
	private static final Logger log = Logger.getLogger(AdminController.class.getName());
	static PersistenceManager pm = PMF.get().getPersistenceManager();

	public List<AdminJdo> getLoginDataFromDB(String email, String password) {

		log.info("inside getLoginDataDB fnction email" + email + "password" + password);

		Query q = pm.newQuery(AdminJdo.class);
		q.setFilter("email == emailParameter && password == passwordParameter");
		q.declareParameters("String emailParameter,String passwordParameter");
		@SuppressWarnings("unchecked")
		List<AdminJdo> loginData = (List<AdminJdo>) q.execute(email, password);

		return loginData;

	}

	public List<AdminJdo> checkEmailExistInDb(String email) {
		// TODO Auto-generated method stub
		log.info("inside checkEmailExistInDb fnction");
		Query q = pm.newQuery(AdminJdo.class);
		q.setFilter("email == emailParameter");
		q.declareParameters("String emailParameter");
		@SuppressWarnings("unchecked")
		List<AdminJdo> checkEmailExist = (List<AdminJdo>) q.execute(email);

		return checkEmailExist;
	}



}
