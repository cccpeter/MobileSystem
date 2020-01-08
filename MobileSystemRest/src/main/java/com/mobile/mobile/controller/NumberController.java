package com.mobile.mobile.controller;

import com.mobile.mobile.entity.PhoneNumber;
import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.manager.BaseFunctionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin")
public class NumberController {
	@Autowired
	private BaseFunctionService baseFunctionService;
	@Autowired
	private com.mobile.mobile.dao.NumberDao NumberDao;
	/**
	 * @Author: cpeter
	 * @Desc: 获取列表
	 * @Date: cretead in 2020/1/6 20:50
	 * @Last Modified: by
	 * @return value
	 */
	@RequestMapping("/getNumberList")
	public ResponseBean getNumberList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select phonenumber.*,user.user_name as user_name from phonenumber left join user on phonenumber.user_id = user.user_id");
		List<Map<String,Object>> result = baseFunctionService.getSqlResult(sql.toString());
		return new  ResponseBean(200, "get data success",result);
	}

	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNumber")
	public ResponseBean addNumber(HttpServletRequest request){
		String phoneNumber = request.getParameter("phoneNumber");
		PhoneNumber PhoneNumber = new PhoneNumber();
		PhoneNumber.setNumber_number(phoneNumber);
		PhoneNumber.setNumber_status("正常");
		NumberDao.save(PhoneNumber);
		return new  ResponseBean(200, "get data success","保存成功");
	}


	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delNumber")
	public ResponseBean delNumber(HttpServletRequest request){
		String ids = request.getParameter("ids");
		if(StringUtils.isNotEmpty(ids)){
			String[] idList = ids.split(",");
			for (int i=0;i<idList.length;i++){
				PhoneNumber PhoneNumber = new PhoneNumber();
				PhoneNumber.setNumber_id(Integer.parseInt(idList[i]));
				NumberDao.delete(PhoneNumber);
			}
			return new ResponseBean(200, "delete success", null);
		}else{
			return new  ResponseBean(500, "delete data error",null);
		}
	}
}
