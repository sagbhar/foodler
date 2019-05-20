package com.foodler.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodler.login.vo.RegisterVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserLoginController {
	
	@Value("${transaction.findUserByEmailId.URL}")
	String transactionUrl;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful added user details"),
			@ApiResponse(code = 500, message = "Internal error"),
			@ApiResponse(code = 404, message = "Error while retrieving the data") })
	@ApiOperation(value = "Login the user", notes = "This API is used to login the user")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RegisterVo login() throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedUser = principal.toString();
		//String transactionUrl = "http://localhost:9012/api/findUserByEmailId/{username}";
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(transactionUrl);

		Map<String, String> userCredentialsMap = new HashMap<>();
		userCredentialsMap.put("username", loggedUser);
		RestTemplate restTemplate = new RestTemplate();
		RegisterVo response = restTemplate.getForObject(builder.build().toString(), RegisterVo.class,userCredentialsMap);
		return response;
	}


}
