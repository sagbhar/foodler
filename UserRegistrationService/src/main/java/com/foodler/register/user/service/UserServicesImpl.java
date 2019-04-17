/**
 * 
 */
package com.foodler.register.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.foodler.register.user.register.vo.RegisterVo;
import com.foodler.register.user.repository.UserRepository;

/**
 * @author sagbhar
 *
 */
@Component
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public RegisterVo saveUserDetails(RegisterVo userVo) {
		return userRepo.save(userVo);
	}

	@Override
	public Boolean isValidUser(String emailId, String password, BCryptPasswordEncoder passwordEncoder) {
		Boolean isValidUser = false;
		RegisterVo userDB = new RegisterVo();
		if( !StringUtils.isEmpty(emailId) && !StringUtils.isEmpty(password)) {
			userDB = userRepo.findByEmailId(emailId);
		}
		if(!StringUtils.isEmpty(userDB.getEmailId()) && userDB.getEmailId().equalsIgnoreCase(emailId) && passwordEncoder.matches(password, userDB.getPassword())){
			isValidUser = true;
		}
		
		return isValidUser;
	}
	
	@Override
	public RegisterVo findUserByUserName(String emailId) {
		return userRepo.findByEmailId(emailId);
	}
}
