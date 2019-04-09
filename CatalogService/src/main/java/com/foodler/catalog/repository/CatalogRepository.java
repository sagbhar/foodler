/**
 * 
 */
package com.foodler.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foodler.catalog.restaurants.vo.RestaurantListVo;
/**
 * @author sagbhar
 *
 */
public interface CatalogRepository extends MongoRepository<RestaurantListVo, String> {
	public RestaurantListVo findByRestaurantName(String restaurant_name);
	public RestaurantListVo findByShopId(String shop_id);
}
