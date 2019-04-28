package com.cc.service.impl;

import org.apache.log4j.Logger;

import com.cc.dao.UserDao;
import com.cc.dao.impl.UserDaoImpl;
import com.cc.pojo.User;
import com.cc.service.UserService;

public class UserServiceImpl implements UserService{
	//声明日志对象
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	//声明dao层对象
	UserDao ud = new UserDaoImpl();
	//用户登录
	@Override
	public User checkUserLogin(String uname, String pwd) {		
		//打印日志
		logger.debug(uname+"发起登录请求");
		User u = ud.checkUserLogin(uname, pwd);
		//判断
		if(u!=null) {
			logger.debug(uname+"登录成功");
		}else {
			logger.debug(uname+"登录失败");
		}
		return u;
	}

}
