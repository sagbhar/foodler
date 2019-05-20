package com.foodler.register.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
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
	public RegisterVo register(@RequestBody RegisterVo user) throws Exception{

		RegisterVo users = new RegisterVo();

		user.setUserId(String.valueOf(sequenceGenerator.generateSequence(users.SEQUENCE_NAME)));
		
		RegisterVo createdUser = userService.saveUserDetails(user);

		return createdUser;	
	}
	
	@GetMapping(value = "/isValidUserCredentials/{emailId}/{password}", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful validated user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while validating the data"),
			@ApiResponse(code = 201, message = "Successfully validated user credentials") })
	@ApiOperation(value = "Get user Details", notes = "This API is used to validate user details")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Boolean isValidUser(@PathVariable (required = true) String emailId,@PathVariable (required = true) String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return userService.isValidUser(emailId,password,passwordEncoder);
	}
	
	@GetMapping(value = "/findUserByEmailId/{emailId}", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful validated user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while validating the data"),
			@ApiResponse(code = 201, message = "Successfully validated user credentials") })
	@ApiOperation(value = "Get user Details", notes = "This API is used to validate user details")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RegisterVo findUserByEmailId(@PathVariable (required = true) String emailId) {
		Map<String,String> userMap= new HashMap<>();
		if(null!=userService.findUserByUserName(emailId)) {
		  userMap.put("userName", userService.findUserByUserName(emailId).getEmailId());
		  userMap.put("password", userService.findUserByUserName(emailId).getPassword());
		  return userService.findUserByUserName(emailId);
		}
		return null;
	}
	
	@GetMapping(value = "/findAllUsers", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful validated user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while validating the data"),
			@ApiResponse(code = 201, message = "Successfully validated user credentials") })
	@ApiOperation(value = "Get All users", notes = "This API is used to fetch all registered users.")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<RegisterVo> findAllUsers() {
		  return userService.findAllUsers();
	}
}
