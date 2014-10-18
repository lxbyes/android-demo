package me.leckie.service;

/**
 * 对用户操作的接口<br>
 * 
 * @author Leckie
 * @date 2014-10-18
 */
public interface UserService {

	/**
	 * 用户登录<br>
	 * 
	 * @param loginName 用户名
	 * @param loginPassword 密码
	 * @throws Exception
	 */
	public void userLogin(String loginName, String loginPassword) throws Exception;
}
