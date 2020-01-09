package com.mobile.mobile.controller;

import com.mobile.mobile.dao.NumberDao;
import com.mobile.mobile.dao.UserDao;
import com.mobile.mobile.entity.PhoneNumber;
import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.entity.User;
import com.mobile.mobile.manager.BaseFunctionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private BaseFunctionService baseFunctionService;

	@Autowired
	private NumberDao numberDao;

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
		sql.append(" select u1.*,u2.user_name as createdByName ,pn.number_number as phone_number,pn.number_status,pn.packagelist_id_list as package_list from user as u1 left join user as u2 on u1.user_createdby = u2.user_id ");
		sql.append(" left join phonenumber as pn on pn.user_id = u1.user_id");
		sql.append(" where u1.user_role='user'");
		List<Map<String,Object>> resultList = baseFunctionService.getSqlResult(sql.toString());
		return new ResponseBean(200, "get data success", resultList);
	}

	/**
	 * 获取用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public ResponseBean getUser(HttpServletRequest request){
		String userNumber = request.getParameter("user_number");
		StringBuffer sql = new StringBuffer();
		sql.append(" select u1.*,u2.user_name as createdByName ,pn.number_number as phone_number,pn.number_status,pn.packagelist_id_list as package_list from user as u1 left join user as u2 on u1.user_createdby = u2.user_id ");
		sql.append(" left join phonenumber as pn on pn.user_id = u1.user_id");
		sql.append(" where u1.user_role='user' and u1.user_number='"+userNumber+"'");
		List<Map<String,Object>> resultList = baseFunctionService.getSqlResult(sql.toString());
		return new ResponseBean(200, "get data success", resultList);
	}
	/**
	 * 新建用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public ResponseBean addUser(HttpServletRequest request){
		String user_number = request.getParameter("user_number");
		String user_name = request.getParameter("user_name");
		String user_addrs = request.getParameter("user_addr");
		String user_password = request.getParameter("user_password");
		String user_sex = request.getParameter("user_sex");
		String user_id = request.getParameter("user_id");
//		开通的套餐列表
		String package_list = request.getParameter("package_list");
//		使用的手机号码
		String number_list = request.getParameter("number_list");
//		判断号码是否重复使用
		PhoneNumber p1 = new PhoneNumber();
		p1.setNumber_id(Integer.parseInt(number_list));
		p1.setUser_id(0);
		Example<PhoneNumber> example1 = Example.of(p1);
		List<PhoneNumber> numberList = numberDao.findAll(example1);
		if(numberList.size()==0){
			return new  ResponseBean(500, "该号码已经被使用了","保存失败");

		}
		User user = new User();
		user.setUser_name(user_name);
		user.setUser_number(user_number);
		user.setUser_password(user_password);
		user.setUser_permission("user");
		user.setUser_role("user");
		user.setUser_sex(user_sex);
		user.setUser_status("正常");
		user.setUser_addrs(user_addrs);
		if(StringUtils.isNotEmpty(user_id)){
			user.setUser_id(Integer.parseInt(user_id));
		}
//		获取该用户是否存在
		User isUser = new User();
		isUser.setUser_number(user_number);
		Example<User> example = Example.of(isUser);
		List<User> userList = userDao.findAll(example);
		int userId = 0;
		if(userList.size()>0){
//			用户已存在
			userId = userList.get(0).getUser_id();
		}else{
//		    用户不存在，新建
			User user1 = userDao.save(user);
			userId = user1.getUser_id();
		}
//		绑定手机号码
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber_id(Integer.parseInt(number_list));
		Example<PhoneNumber> example2 = Example.of(phoneNumber);
		List<PhoneNumber> numberLis1 = numberDao.findAll(example2);
		if(numberLis1.size()>0){
			PhoneNumber phoneNumber1 = numberLis1.get(0);
			phoneNumber1.setPackagelist_id_list(package_list);
			phoneNumber1.setUser_id(userId);
			numberDao.save(phoneNumber1);
		}
		return new  ResponseBean(200, "get data success","保存成功");
	}

	/**
	 * 停用号码
	 *
	 */
	@RequestMapping({"/stopNumber"})
	public ResponseBean stopNumber(HttpServletRequest request){
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber_number(request.getParameter("phone_number"));
		phoneNumber.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		Example<PhoneNumber> example = Example.of(phoneNumber);
		List<PhoneNumber> numberList = numberDao.findAll(example);
		if(numberList.size()>0){
			PhoneNumber phoneNumber1 = numberList.get(0);
			phoneNumber1.setNumber_status("停用");
			numberDao.save(phoneNumber1);
		}
		return new  ResponseBean(200, "停用成功",null);
	}
	/**
	 * 注销号码
	 *
	 */
	@RequestMapping({"/destoryNumber"})
	public ResponseBean destoryNumber(HttpServletRequest request){
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber_number(request.getParameter("phone_number"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String user_number = request.getParameter("user_number");
		phoneNumber.setUser_id(userId);
		User user = new User();
		user.setUser_id(userId);
		Example<User> example = Example.of(user);
		Optional<User> optionalUser = userDao.findOne(example);
		if(optionalUser.isPresent()){
			if (StringUtils.equals(optionalUser.get().getUser_number(),user_number)){
				Example<PhoneNumber> example1 = Example.of(phoneNumber);
				List<PhoneNumber> numberList = numberDao.findAll(example1);
				if(numberList.size()>0){
					PhoneNumber phoneNumber1 = numberList.get(0);
					if(!StringUtils.equals(phoneNumber1.getNumber_status(),"停用")){
						return new  ResponseBean(200, "注销失败，号码需要进行停用再注销",null);
					}
					phoneNumber1.setNumber_status("注销");
					numberDao.save(phoneNumber1);
					return new  ResponseBean(200, "注销成功",null);
				}
			}
		}
		return new  ResponseBean(200, "注销失败，请正确输入用户账号",null);
	}
	/**
	 * 获取号码列表
	 * @return
	 */
	@RequestMapping("/getNumberList")
	public ResponseBean getNumberList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select phonenumber.*,user.user_name as user_name from phonenumber left join user on phonenumber.user_id = user.user_id where phonenumber.user_id=0");
		List<Map<String,Object>> result = baseFunctionService.getSqlResult(sql.toString());
		return new  ResponseBean(200, "get data success",result);
	}

	@RequestMapping({"/testRole"})
	@RequiresRoles("admin")
	public ResponseBean getClasses(@RequestParam("username") String username){

		return new ResponseBean(200, "get data success", username);
	}
}
