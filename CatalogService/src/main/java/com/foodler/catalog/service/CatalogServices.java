/**
 * 
 */
package com.foodler.catalog.service;

import java.util.List;

import com.foodler.catalog.restaurants.vo.RestaurantListVo;

/**
 * @author sagbhar
 *
 */
public interface CatalogServices {

	RestaurantListVo saveRestaurantFoodDetails(RestaurantListVo resVo);
	RestaurantListVo findFoodItemsByShopId(String shop_id);
	RestaurantListVo addOrUpdateFoodItems(String shop_id,String foodItem,Double price,String inventory,Boolean isUpdate);
	List<RestaurantListVo> findAllRestaurants();
}
