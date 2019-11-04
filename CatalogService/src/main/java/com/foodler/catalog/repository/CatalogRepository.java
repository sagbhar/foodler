/**
 * 
 */
package com.foodler.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foodler.catalog.restaurants.vo.RestaurantListVo;
/**
 * @author sagbhar
 *
 */
@Repository
public interface CatalogRepository extends MongoRepository<RestaurantListVo, String> {
	public RestaurantListVo findByRestaurantName(String restaurant_name);
	public RestaurantListVo findByShopId(String shop_id);
}
