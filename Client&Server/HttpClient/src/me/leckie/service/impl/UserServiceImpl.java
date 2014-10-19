package me.leckie.service.impl;

import java.util.ArrayList;
import java.util.List;

import me.leckie.activity.LoginActivity;
import me.leckie.exception.ServiceRulesException;
import me.leckie.service.UserService;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

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

		/*// 创建HttpClient对象
		HttpClient client = new DefaultHttpClient();

		*//**
		 * uri:URL地址 http://10.68.2.110:8080/HttpServer/login.do
		 * 
		 * GET传参：
		 *//*
		String uri = "http://10.68.2.110:8080/HttpServer/login.do?loginName="
				+ loginName + "&loginPassword=" + loginPassword;
		HttpGet get = new HttpGet(uri);
		
		// 服务器响应
		HttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		
		if(statusCode != HttpStatus.SC_OK) {
			throw new ServiceRulesException(LoginActivity.MSG_SERVER_FAILED);
		}
		
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		
		if("success".equals(result)) {
			// 登录成功
		} else {
			throw new ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED);
		}*/
		
		HttpParams params = new BasicHttpParams();
		// 设置请求字符集
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		// 设置请求连接超时时间-->ConnectionTimeoutException
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		// 设置响应超时时间-->SocketTimeoutException
		HttpConnectionParams.setSoTimeout(params, 3000);
		
		
		SchemeRegistry schreg = new SchemeRegistry();
		// http协议注册
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		// https协议注册
		schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 433));
		
		ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schreg);
		
		HttpClient client = new DefaultHttpClient(conman, params);
		
		String uri = "http://10.68.2.110:8080/HttpServer/login.do";
		
		HttpPost post = new HttpPost(uri);
		
		NameValuePair parameterLoginName = new BasicNameValuePair("loginName", loginName);
		NameValuePair parameterLoginPassword = new BasicNameValuePair("loginPassword", loginPassword);
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(parameterLoginName);
		parameters.add(parameterLoginPassword);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		
		HttpResponse response = client.execute(post);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode != HttpStatus.SC_OK) {
			throw new ServiceRulesException(LoginActivity.MSG_SERVER_FAILED);
		}
		
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		if("success".equals(result)) {
			// 登录成功
		} else {
			throw new ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED);
		}
		
		/*
		 * Thread.sleep(3000); // 等待3s
		 * 
		 * if(loginName.equals("leckie") && loginPassword.equals("123")) {
		 * 
		 * } else { throw new
		 * ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED); }
		 */
	}
}
