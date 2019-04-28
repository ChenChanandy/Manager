package com.cc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cc.pojo.User;
import com.cc.service.UserService;
import com.cc.service.impl.UserServiceImpl;


public class UserServlet extends HttpServlet {
	//声明日志对象
	Logger logger = Logger.getLogger(UserServlet.class);
	
	//获取service层对象
	UserService us = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取操作符
		String oper = req.getParameter("oper");		
		if("login".equals(oper)) {
			//调用登录处理方法
			checkUserLogin(req,resp);
		}else if("out".equals(oper)) {
			//退出登录
			userOut(req,resp);
		}else if("register".equals(oper)) {
			//调用注册处理方法
		}else {
			logger.debug("没有找到对应的操作符："+oper);
		}		
	}
	
	//退出功能
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs = req.getSession();		
		logger.debug(((User)hs.getAttribute("user")).getUname()+"退出登录");
		//强制销毁session
		hs.invalidate();
		//重定向
		resp.sendRedirect("/mg/login.jsp");
	}

	//处理登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");		
		//处理请求信息
		  //校验
		User u = us.checkUserLogin(uname, pwd);
		//响应处理结果
		if(u!=null) {
			//获取session对象
			HttpSession hs = req.getSession();
			//将用户数据存储到session
			hs.setAttribute("user", u);
			//重定向
			resp.sendRedirect("/mg/main/main.jsp");
			return;
		}else {
			//添加标识符到request中
			req.setAttribute("flag", 0);
			//请求转发
			req.getRequestDispatcher("/login.jsp").forward(req,resp);
			return;
		}
				
	}
	
	
}
