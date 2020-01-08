package com.mobile.mobile.controller;

import com.mobile.mobile.dao.RentDao;
import com.mobile.mobile.entity.Rent;
import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.manager.BaseFunctionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class RentController {
	@Autowired
	private BaseFunctionService baseFunctionService;
	@Autowired
	private RentDao RentDao;
	/**
	 * @Author: cpeter
	 * @Desc: 获取列表
	 * @Date: cretead in 2020/1/6 20:50
	 * @Last Modified: by
	 * @return value
	 */
	@RequestMapping("/getRentList")
	public ResponseBean getRentList(){
		List<Rent> RentList = RentDao.findAll();
		return new  ResponseBean(200, "get data success",RentList);
	}

	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/addRent")
	public ResponseBean addRent(HttpServletRequest request){
		String rent_id = request.getParameter("rent_id");
		String rent_content = request.getParameter("rent_content");
		String rent_money = request.getParameter("rent_money");

		Rent Rent = new Rent();
		if(StringUtils.isNotEmpty(rent_id)){
			Rent.setRent_id(Integer.parseInt(rent_id));
		}
		Rent.setRent_content(rent_content);
		Rent.setRent_money(rent_money);
		RentDao.save(Rent);
		return new  ResponseBean(200, "get data success","保存成功");
	}


	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delRent")
	public ResponseBean delRent(HttpServletRequest request){
		String ids = request.getParameter("ids");
		if(StringUtils.isNotEmpty(ids)){
			String[] idList = ids.split(",");
			for (int i=0;i<idList.length;i++){
				Rent Rent = new Rent();
				Rent.setRent_id(Integer.parseInt(idList[i]));
				RentDao.delete(Rent);
			}
			return new ResponseBean(200, "delete success", null);
		}else{
			return new  ResponseBean(500, "delete data error",null);
		}
	}
}
