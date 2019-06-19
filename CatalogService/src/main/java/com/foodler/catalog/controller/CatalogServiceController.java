/**
 * 
 */
package com.foodler.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodler.catalog.restaurants.vo.RestaurantListVo;
import com.foodler.catalog.service.CatalogServices;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author sagbhar
 *
 */

@RestController
@RequestMapping("/api")
public class CatalogServiceController {

	@Autowired 
	private CatalogServices catalogService;

	@PostMapping(value = "/addRestaurantDetails",produces = "application/json")
	@ApiOperation(value = "Add Restaurant Details", notes = "This API is used to add restaurant details")	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful added restaurant details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestaurantListVo  addRestaurant(@RequestBody RestaurantListVo restaurant) {
		RestaurantListVo vo= catalogService.saveRestaurantFoodDetails(restaurant);
		return vo;
	}
	
	@GetMapping(value = "/getRestaurantDetails/{shop_id}",produces = "application/json")
	@ApiOperation(value = "Get Restaurant Details", notes = "This API is used to get restaurant details")	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieved restaurant details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestaurantListVo  getRestaurantByShopIdAndShopName(@PathVariable(required = true) String shop_id) {
		RestaurantListVo vo= catalogService.findFoodItemsByShopId(shop_id);
		return vo;
	}
	
	@GetMapping(value = "/getAllRestaurants",produces = "application/json")
	@ApiOperation(value = "Get All Restaurant Details", notes = "This API is used to get all the restaurant details")	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieved restaurant details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<RestaurantListVo>  getAllRestaurants() {
		return catalogService.findAllRestaurants();
	}
	
	@PutMapping(value = "/addOrUpdateRestaurantFoodItems/{shop_id}/{foodItem}/{price}/{inventory}/{isUpdate}",produces = "application/json")
	@ApiOperation(value = "Add or Update Restaurant Details", notes = "This API is used to add or update restaurant details")	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful updated restaurant details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestaurantListVo  addOrUpdateRestaurantFoodItems(@PathVariable(required = true) String shop_id,@PathVariable(required = true) String foodItem,@PathVariable(required = true) Double price,@PathVariable(required = true) String inventory,@PathVariable(required = true) Boolean isUpdate) {
		RestaurantListVo vo= catalogService.addOrUpdateFoodItems(shop_id,foodItem,price,inventory,isUpdate);
		return vo;
	}
	
	}
