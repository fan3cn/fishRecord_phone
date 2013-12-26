package com.fan3cn.cardlist;

import java.io.Serializable;

/**
 * @author Eric
 *
 */
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1524448624830406675L;

	/** id */
	private int id;
	
	/** 公司名称 */
	private String name;
	
	/** 公司电话 */
	private String phone;
	
	/** 公司传真 */
	private String fax;
	
	/** 电子邮件 */
	private String email;
	
	/** 公司地址 */
	private String address;
	
	/** 是否为默认公司 */
	private String is_default;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the is_default
	 */
	public String getIs_default() {
		return is_default;
	}
	/**
	 * @param is_default the is_default to set
	 */
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
