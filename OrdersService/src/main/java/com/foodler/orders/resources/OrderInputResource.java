package com.foodler.orders.resources;

import java.util.List;

public class OrderInputResource {

	private String orderId;
	private String userId;
	private Double totalAmt;
	private String status;
	private List<OrderItemInputResource> orderItems;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<OrderItemInputResource> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemInputResource> orderItems) {
		this.orderItems = orderItems;
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
	
	
	
}
