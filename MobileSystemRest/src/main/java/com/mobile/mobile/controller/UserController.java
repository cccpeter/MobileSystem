package com.mobile.mobile.controller;

import com.mobile.mobile.dao.UserDao;
import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.entity.User;
import com.mobile.mobile.manager.BaseFunctionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private BaseFunctionService baseFunctionService;

	@Autowired
	private UserDao userDao;

	@RequestMapping({"/getUserDetail"})
	public String getUserDetail(HttpServletRequest request){
		String userNumber = request.getParameter("userNumber");
		String sql = "select * from User where user_number = '"+userNumber+"'";
		List<Map<String,Object>> list = baseFunctionService.getSqlResult(sql);
		list.forEach(item->System.out.println(item.get("user_name")));
		return (String) list.get(0).get("user_name");
	}
	@RequestMapping({"/getUserDetail1"})
	public String getUserDetail1(HttpServletRequest request){
		User user = new User();
		user.setUser_number(request.getParameter("userNumber"));
		org.springframework.data.domain.Example<User> example = Example.of(user);
		List<User> list = userDao.findAll(example);
		 return list.get(0).getUser_name();
	}

	/**
	 * 获取用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserList")
	public ResponseBean getUserList(HttpServletRequest request){
		StringBuffer sql = new StringBuffer();
		sql.append(" select u1.*,u2.user_name as createdByName from user as u1 left join user as u2 on u1.user_createdby = u2.user_id  where u1.user_role='user'");
		List<Map<String,Object>> resultList = baseFunctionService.getSqlResult(sql.toString());
		return new ResponseBean(200, "get data success", resultList);
	}

	@RequestMapping({"/testRole"})
	@RequiresRoles("admin")
	public ResponseBean getClasses(@RequestParam("username") String username){

		return new ResponseBean(200, "get data success", username);
	}
}
