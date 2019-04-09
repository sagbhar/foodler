package com.foodler.register.user.register.vo;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class RegisterVo {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	
	private String mobNum;
	@Email
	private String emailId;
	private String password;
	private String userType;
	private String shopId;
	private String shopName;
   public RegisterVo() {
	   super();
   }
	public RegisterVo(String userId, String firstName, String lastName, String mobNum, String emailId,
			String password, String userType, String shopId, String shopName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobNum = mobNum;
		this.emailId = emailId;
		this.password = password;
		this.userType = userType;
		this.shopId = shopId;
		this.shopName = shopName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobNum() {
		return mobNum;
	}

	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
  
}
