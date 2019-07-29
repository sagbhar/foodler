package com.foodler.catalog.restaurants.vo;

public class RestaurantFoodItems {

	private String foodItemId;
	private String foodItemImageUrl;
	private String foodItem;
	private Double price;
	private String inventory;
	
	
	

	public String getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(String foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getFoodItemImageUrl() {
		return foodItemImageUrl;
	}

	public void setFoodItemImageUrl(String foodItemImageUrl) {
		this.foodItemImageUrl = foodItemImageUrl;
	}

	public RestaurantFoodItems(String foodItemId, String foodItemImageUrl, String foodItem, Double price,
			String inventory) {
		super();
		this.foodItemId = foodItemId;
		this.foodItemImageUrl = foodItemImageUrl;
		this.foodItem = foodItem;
		this.price = price;
		this.inventory = inventory;
	}

	public RestaurantFoodItems() {
		super();
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	
	
	 
}
