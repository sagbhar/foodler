package com.foodler.register.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodler.register.sequence.SequenceGeneratorService.SequenceGeneratorService;
import com.foodler.register.user.register.vo.RegisterVo;
import com.foodler.register.user.service.UserServicesImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserServiceController {
	
	@Autowired
	private UserServicesImpl userService;
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Value("${userType.Vendor}")
	private String vendorText;
	
	@PostMapping(value = "/register", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful added user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ApiOperation(value = "Add user Details", notes = "This API is used to add user details")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RegisterVo register(@RequestBody RegisterVo user) {

		RegisterVo users = new RegisterVo();

		user.setUserId(String.valueOf(sequenceGenerator.generateSequence(users.SEQUENCE_NAME)));
		if(user.getUserType().equalsIgnoreCase(vendorText)) {
		 user.setShopId(String.valueOf(user.getUserId()));
		}
		try {
			user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
		}
		RegisterVo createdUser = userService.saveUserDetails(user);

		return createdUser;
	}
	
	@GetMapping(value = "/getUserDetails", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful added user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ApiOperation(value = "Get user Details", notes = "This API is used to get user details")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RegisterVo getUserByEmailId(@PathVariable String emailId) {
		return userService.findUserbyEmailId(emailId);
	}
}
