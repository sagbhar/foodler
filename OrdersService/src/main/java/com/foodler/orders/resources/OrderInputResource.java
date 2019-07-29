package com.foodler.orders.resources;

import java.util.List;

public class OrderInputResource {

	private String orderId;
	private String userId;
	private Double totalAmt;
	private String status;
	private List<OrderItemInputResource> orderItemsDetails;
	
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
	public List<OrderItemInputResource> getOrderItemsDetails() {
		return orderItemsDetails;
	}
	public void setOrderItemsDetails(List<OrderItemInputResource> orderItemsDetails) {
		this.orderItemsDetails = orderItemsDetails;
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
