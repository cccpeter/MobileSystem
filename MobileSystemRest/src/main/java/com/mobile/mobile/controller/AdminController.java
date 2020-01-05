package com.mobile.mobile.controller;

import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.manager.BaseFunctionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private BaseFunctionService baseFunctionService;

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
		userSql.append(" select count(*) as num,user_role from user  group by user_role");
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
}
