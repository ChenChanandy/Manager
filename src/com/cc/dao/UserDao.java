package com.cc.dao;

import com.cc.pojo.User;

public interface UserDao {
		
	User checkUserLogin(String uname,String pwd);
}
