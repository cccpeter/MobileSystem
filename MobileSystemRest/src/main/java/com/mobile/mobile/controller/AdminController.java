package com.mobile.mobile.controller;

import com.mobile.mobile.dao.UserDao;
import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.entity.User;
import com.mobile.mobile.manager.BaseFunctionService;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private BaseFunctionService baseFunctionService;

	@Autowired
	private UserDao userDao;
	/**
	 * 获取系统数据
	 * @param request
	 * @return
	 */
	@RequestMapping({"/getSysData"})
	@RequiresRoles("admin")
	public ResponseBean getUserDetail(HttpServletRequest request){
		StringBuffer userSql = new StringBuffer();
		StringBuffer packageSql = new StringBuffer();
		userSql.append(" select count(*) as num,user_role from user group by user_role");
		packageSql.append(" select count(*) as packageNum from packagelist");
		List<Map<String,Object>> listUser = baseFunctionService.getSqlResult(userSql.toString());
		List<Map<String,Object>> listPack = baseFunctionService.getSqlResult(packageSql.toString());
		HashMap<String,String> resultMap = new HashMap<>();
		if(!listUser.isEmpty() && !listPack.isEmpty()){
			listUser.forEach(item->{
				if(StringUtils.equals("user", (String)item.get("user_role"))){
					resultMap.put("userNum", String.valueOf(item.get("num")));
				}
				if(StringUtils.equals("sale", (String)item.get("user_role"))){
					resultMap.put("saleNum", String.valueOf(item.get("num")));
				}
				if(StringUtils.equals("admin", (String)item.get("user_role"))){
					resultMap.put("adminNum", String.valueOf(item.get("num")));
				}
			});
			resultMap.put("packageNum", String.valueOf(listPack.get(0).get("packageNum")));
		}
		return new ResponseBean(200, "get data success",resultMap);
	}
	/**
	 * @Author: cpeter
	 * @Desc: 获取业务人员的列表
	 * @Date: cretead in 2020/1/5 20:57
	 * @Last Modified: by
	 * @return value
	 */
	@RequestMapping("/getSaleList")
	public ResponseBean getSaleList(){
		User user = new User();
		user.setUser_role("sale");
		Example<User> userExample = Example.of(user);
		List<User> userList = userDao.findAll(userExample);
		return new  ResponseBean(200, "get data success",userList);
	}

	/**
	 * 保存用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public ResponseBean addUser(HttpServletRequest request){
		String user_number = request.getParameter("user_number");
		String user_name = request.getParameter("user_name");
		String user_addrs = request.getParameter("user_addrs");
		String user_password = request.getParameter("user_password");
		String user_sex = request.getParameter("user_sex");
		String user_id = request.getParameter("user_id");
		User user = new User();
		user.setUser_name(user_name);
		user.setUser_number(user_number);
		user.setUser_password(user_password);
		user.setUser_permission("sale");
		user.setUser_role("sale");
		user.setUser_sex(user_sex);
		user.setUser_status("正常");
		user.setUser_addrs(user_addrs);
		if(StringUtils.isNotEmpty(user_id)){
			user.setUser_id(Integer.parseInt(user_id));
		}
		userDao.save(user);

		return new  ResponseBean(200, "get data success","保存成功");
	}

	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public ResponseBean getUser(HttpServletRequest request){
		String user_id = request.getParameter("user_id");
		User user = new User();
		user.setUser_id(Integer.parseInt(user_id));
		Example<User> example = Example.of(user);
		Optional<User> optionalUser = userDao.findOne(example);
		if(optionalUser.isPresent()){
			return new  ResponseBean(200, "get data success",optionalUser.get());
		}else{
			return new  ResponseBean(500, "get data error",null);
		}
	}

	
}
