package me.leckie.service.impl;


import me.leckie.activity.LoginActivity;
import me.leckie.exception.ServiceRulesException;
import me.leckie.service.UserService;


/**
 * 用户操作方法的实现<br>
 * 
 * @author Leckie
 * @date 2014-10-18
 */
public class UserServiceImpl implements UserService {
	
	private static final String TAG = "UserServiceImpl";

	@Override
	public void userLogin(String loginName, String loginPassword)
			throws Exception {
		
		Thread.sleep(3000); // 等待3s
		
		if(loginName.equals("leckie") && loginPassword.equals("123")) {
			
		} else {
			throw new ServiceRulesException(LoginActivity.LOGIN_FAILED_MSG);
		}

	}
}
