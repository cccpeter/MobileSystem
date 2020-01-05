package com.mobile.mobile.service;

import com.mobile.mobile.dao.UserDao;
import com.mobile.mobile.entity.User;
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
	private UserDao userDao;

	public User getUser(String usernumber) {
		User user = new User();
		user.setUser_number(usernumber);
		org.springframework.data.domain.Example<User> example = Example.of(user);
		List<User> list = userDao.findAll(example);
		// 没有此用户直接返回null
		if (list.isEmpty())
			return null;
		return user;
	}
	public User getUserinfo(String usernumber){
		User user = new User();
		user.setUser_number(usernumber);
		org.springframework.data.domain.Example<User> example = Example.of(user);
		List<User> list = userDao.findAll(example);
		// 没有此用户直接返回null
		if (list.isEmpty())
			return null;
		return list.get(0);
	}
}
