/**
 * 
 */
package com.foodler.register.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${userType.Vendor}")
	private String vendorText;

	@Override
	public RegisterVo saveUserDetails(RegisterVo user) throws Exception {
		if (user.getUserType().equalsIgnoreCase(vendorText) && !StringUtils.isEmpty(user.getShopName())) {
			user.setShopId(String.valueOf(user.getUserId()));
		} else
			throw new Exception("Please provice shopName");

		List<RegisterVo> registeredUsers = findAllUsers();
		String shopName=user.getShopName();
		registeredUsers.stream().forEach(regUser -> {
			if (!StringUtils.isEmpty(regUser.getShopName()) && regUser.getShopName().compareToIgnoreCase(shopName) == 0) {
				throw  new IllegalArgumentException("Admin for the shop " + user.getShopName() + " already exists");
				
			}
			if (regUser.getEmailId().compareToIgnoreCase(user.getEmailId()) == 0) {
				throw  new IllegalArgumentException("EmailId already exists");
			}
		});
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepo.save(user);
	}

	@Override
	public Boolean isValidUser(String emailId, String password, BCryptPasswordEncoder passwordEncoder) {
		Boolean isValidUser = false;
		RegisterVo userDB = new RegisterVo();
		if (!StringUtils.isEmpty(emailId) && !StringUtils.isEmpty(password)) {
			userDB = userRepo.findByEmailId(emailId);
		}
		if (null != userDB && !StringUtils.isEmpty(userDB.getEmailId()) && userDB.getEmailId().equalsIgnoreCase(emailId)
				&& passwordEncoder.matches(password, userDB.getPassword())) {
			isValidUser = true;
		}

		return isValidUser;
	}

	@Override
	public RegisterVo findUserByUserName(String emailId) {
		return userRepo.findByEmailId(emailId);
	}

	@Override
	public List<RegisterVo> findAllUsers() {
		return userRepo.findAll();
	}
}
