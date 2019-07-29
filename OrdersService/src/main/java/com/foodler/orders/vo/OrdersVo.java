package com.foodler.orders.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="orders")
public class OrdersVo {
	
	@Transient
	public static final String SEQUENCE_NAME = "orders_sequence";
	
	@Id
	private String orderId;
	private String userId;
	private Double totalAmt;
	private String status;
	
	public OrdersVo(String orderId, String userId, Double totalAmt, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmt = totalAmt;
		this.status = status;
	}
	
	public OrdersVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
