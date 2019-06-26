/**
 * 
 */
package com.foodler.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodler.catalog.repository.CatalogRepository;
import com.foodler.catalog.restaurants.vo.RestaurantFoodItems;
import com.foodler.catalog.restaurants.vo.RestaurantListVo;

/**
 * @author sagbhar
 *
 */
@Component
public class CatalogServicesImpl implements CatalogServices {

	@Autowired
	private CatalogRepository catalogRepo;
	
	@Override
	public RestaurantListVo saveRestaurantFoodDetails(RestaurantListVo resVo) {
		return catalogRepo.save(resVo);
	}

	
	@Override
	public RestaurantListVo findFoodItemsByShopId(String shop_id) {
		return catalogRepo.findByShopId(shop_id);
	}


	@Override
	public RestaurantListVo addOrUpdateFoodItems(String shop_id,String foodItem,Double price,String inventory,Boolean isUpdate,String imageURL) {
		RestaurantListVo vo = findFoodItemsByShopId(shop_id);
		RestaurantFoodItems resFood = new RestaurantFoodItems();
		resFood.setFoodItem(foodItem);
		resFood.setInventory(inventory);
		resFood.setPrice(price);
		resFood.setInventory(imageURL);
		List<RestaurantFoodItems> foodList =  vo.getRestaurantFoodItems();
		if(isUpdate) {
			foodList.stream().forEach(item ->{
				if(item.getFoodItem().equalsIgnoreCase(foodItem)) {
					item.setInventory(inventory);
					item.setPrice(price);
				}	
			}); 
		} else {
			foodList.add(resFood);
		}
		vo.setRestaurantFoodItems(foodList);
		catalogRepo.save(vo);
		return vo;
	}
	
	
	@Override
	public List<RestaurantListVo> findAllRestaurants() {
		return catalogRepo.findAll();
	}


}
