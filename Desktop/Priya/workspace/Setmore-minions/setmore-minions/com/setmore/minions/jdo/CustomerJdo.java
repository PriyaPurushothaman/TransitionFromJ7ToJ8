package com.setmore.minions.jdo;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CustomerJdo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Persistent
	private String username;
	@Persistent
	private String useremail;
	@Persistent
	private String customerName;
	@Persistent
	private String customerEmail;
	@Persistent
	private String customerMobile;
	@Persistent
	private String 	 customerZip;
	@Persistent
	private String 	 customerAddress;
	@Persistent
	private long 	adminId;
	
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getCustomername() {
		return customerName;
	}
	public void setCustomername(String customername) {
		this.customerName = customername;
	}
	public String getCustomeremail() {
		return customerEmail;
	}
	public void setCustomeremail(String customeremail) {
		this.customerEmail = customeremail;
	}
	public String getMobile() {
		return getMobile();
	}
	public void setMobile(String mobile) {
		this.customerMobile = mobile;
	}
	public String getZip() {
		return customerZip;
	}
	public void setZip(String zip) {
		this.customerZip = zip;
	}
	public String getAddress() {
		return customerAddress;
	}
	public void setAddress(String address) {
		this.customerAddress = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
