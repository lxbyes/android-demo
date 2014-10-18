package com.leckie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * android登录服务器Demo<br>
 * 
 * @author Leckie
 * @date 2014年10月16日
 */
@WebServlet( "/login.do" )
public class LoginServlet4AndroidClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---->doGet");
		this.doPost(request, response);
	}

	/**
	 * 模拟登录业务
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        IOException {
		request.setCharacterEncoding("UTF-8");
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		System.out.println("loginName:" + loginName + " loginPassword:" + loginPassword);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;

		try {
			pw = response.getWriter();

			if (loginName != null && loginPassword != null && "Leckie".equals(loginName) && "123".equals(loginPassword)) {
				// 登录成功
				pw.print("success");
			} else {
				// 登录失败
				pw.print("failed");
			}

		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

}
