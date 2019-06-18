/**
 * 
 */
package com.foodler.catalog.restaurants.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sagbhar
 *
 */
@Document(collection="restaurants")
public class RestaurantListVo {

	@Id
	private String shopId;
	private String restaurantName;
	private List<RestaurantFoodItems> restaurantFoodItems;
	
	public RestaurantListVo(String restaurantName, List<RestaurantFoodItems> restaurantFoodItems,
			String shopId) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantFoodItems = restaurantFoodItems;
		this.shopId = shopId;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	public List<RestaurantFoodItems> getRestaurantFoodItems() {
		return restaurantFoodItems;
	}

	public void setRestaurantFoodItems(List<RestaurantFoodItems> restaurantFoodItems) {
		this.restaurantFoodItems = restaurantFoodItems;
	}

	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
	
}
