package com.cg.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Login;
import com.cg.mts.entity.User;
import com.cg.mts.repoImpl.QueryClass;

@Service
public class LoginService {
	
	@Autowired
	private QueryClass qcp;

	private Login logData = new Login();

	public Login loginWithData(String username, String password) throws Exception {
		User user = qcp.findByUserName(username, password);
		if (!user.getPassword().equals(password))
			throw new Exception("Login Data Invalid");
		logData.setLoginStatus(true);
		logData.setUser(user);
		return logData;
	}

	public Login logoutPresentUser() {
		if (logData.isLoginStatus()) {
			logData.setLoginStatus(false);
		}
		return logData;
	}

	public boolean loginStatus() {
		return logData.isLoginStatus();
	}

	public String getRole() {
		return logData.getRole();
	}

}
