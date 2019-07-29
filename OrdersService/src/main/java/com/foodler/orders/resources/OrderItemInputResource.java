package com.foodler.orders.resources;

public class OrderItemInputResource {

	private String orderItemId;
	private String orderId;
	private String userId;
	private String foodItemId;
	private Double orderItemsPrice;
	private String inventory;
	private String status;
	
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
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
	public String getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(String foodItemId) {
		this.foodItemId = foodItemId;
	}
	public Double getOrderItemsPrice() {
		return orderItemsPrice;
	}
	public void setOrderItemsPrice(Double orderItemsPrice) {
		this.orderItemsPrice = orderItemsPrice;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
