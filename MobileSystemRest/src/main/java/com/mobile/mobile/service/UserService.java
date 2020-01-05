package com.mobile.mobile.service;

import com.mobile.mobile.dao.userDao;
import com.mobile.mobile.entity.user;
import com.mobile.mobile.manager.BaseFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private BaseFunctionService baseFunctionService;

	@Autowired
	private userDao userDao;

	public user getUser(String usernumber) {
		user user = new user();
		user.setUser_number(usernumber);
		org.springframework.data.domain.Example<user> example = Example.of(user);
		List<user> list = userDao.findAll(example);
		// 没有此用户直接返回null
		if (list.isEmpty())
			return null;
		return user;
	}
	public user getUserinfo(String usernumber){
		user user = new user();
		user.setUser_number(usernumber);
		org.springframework.data.domain.Example<user> example = Example.of(user);
		List<user> list = userDao.findAll(example);
		// 没有此用户直接返回null
		if (list.isEmpty())
			return null;
		return list.get(0);
	}
}
