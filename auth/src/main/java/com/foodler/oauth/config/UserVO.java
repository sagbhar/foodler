package com.foodler.oauth.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service(value = "userService")
public class UserVO implements UserDetailsService{
	
	
	String emailId, password, role, access_token;
	
	@Value("${transaction.findUserByEmailId.URL}")
	String transactionUrl;
	
	 public UserVO() {
		 super();
	 }
    public UserVO(String emailId, String password, String role) {
        super();
        this.emailId = emailId;
        this.password = password;
        this.role = role;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getEmailId() {
        return emailId;
    }
 
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getAccess_token() {
        return access_token;
    }
 
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 //String transactionUrl = "http://localhost:9012/api/findUserByEmailId/{username}";
		 UriComponentsBuilder builder = UriComponentsBuilder
                 .fromUriString(transactionUrl);
             
             Map<String, String> userCredentialsMap = new HashMap<>();
             userCredentialsMap.put("username", username);
             RestTemplate restTemplate = new RestTemplate();
             RegisterVo response = restTemplate.getForObject(builder.build().toString(), RegisterVo.class,userCredentialsMap);
             if (StringUtils.isEmpty(response.getEmailId())) {
                 throw new UsernameNotFoundException("Invalid username or password.");
             }
             return new org.springframework.security.core.userdetails.User(String.valueOf(response.getEmailId()),String.valueOf(response.getPassword()), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
 
}


