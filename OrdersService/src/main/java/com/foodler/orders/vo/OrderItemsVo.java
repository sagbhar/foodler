package com.foodler.orders.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="orderItems")
public class OrderItemsVo {
	
	@Transient
	public static final String SEQUENCE_NAME = "orderItems_sequence";
	@Id
	private String orderItemId;
	private String orderId;
	private String userId;
	private String foodItemId;
	private Double orderItemsPrice;
	private String inventory;
	private String status;
	
	

	public OrderItemsVo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrderItemsVo(String orderItemId, String orderId, String userId,String foodItemId, Double orderItemsPrice, String inventory,
			String status) {
		super();
		this.orderItemId = orderItemId;
		this.userId = userId;
		this.orderId= orderId;
		this.foodItemId = foodItemId;
		this.orderItemsPrice = orderItemsPrice;
		this.inventory = inventory;
		this.status = status;
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

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
