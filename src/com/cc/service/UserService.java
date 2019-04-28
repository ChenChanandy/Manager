package com.cc.service;

import com.cc.pojo.User;

public interface UserService {
	
	User checkUserLogin(String uname,String pwd);
}
