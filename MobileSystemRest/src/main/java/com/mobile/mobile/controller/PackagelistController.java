package com.mobile.mobile.controller;

import com.mobile.mobile.dao.PackagelistDao;
import com.mobile.mobile.entity.Packagelist;
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
public class PackagelistController {
	@Autowired
	private BaseFunctionService baseFunctionService;
	@Autowired
	private PackagelistDao packagelistDao;
	/**
	 * @Author: cpeter
	 * @Desc: 获取列表
	 * @Date: cretead in 2020/1/6 20:50
	 * @Last Modified: by
	 * @return value
	 */
	@RequestMapping("/getPackageList")
	public ResponseBean getPackagelistList(){
		List<Packagelist> PackagelistList = packagelistDao.findAll();
		return new  ResponseBean(200, "get data success",PackagelistList);
	}

	/**
	 * 保存用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPackagelist")
	public ResponseBean addPackagelist(HttpServletRequest request){
		String package_id = request.getParameter("package_id");
		String package_monthRent = request.getParameter("package_monthRent");
		String package_minCharge = request.getParameter("package_minCharge");
		String package_msgCharge = request.getParameter("package_msgCharge");
		String package_totalTraffic = request.getParameter("package_totalTraffic");
		Packagelist packagelist = new Packagelist();
		if(StringUtils.isNotEmpty(package_id)){
			packagelist.setPackage_id(Integer.parseInt(package_id));
		}
		packagelist.setPackage_minCharge(package_minCharge);
		packagelist.setPackage_monthRent(package_monthRent);
		packagelist.setPackage_msgCharge(package_msgCharge);
		packagelist.setPackage_totalTraffic(package_totalTraffic);
		packagelistDao.save(packagelist);
		return new  ResponseBean(200, "get data success","保存成功");
	}


	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delPackagelist")
	public ResponseBean delPackagelist(HttpServletRequest request){
		String ids = request.getParameter("ids");
		if(StringUtils.isNotEmpty(ids)){
			String[] idList = ids.split(",");
			for (int i=0;i<idList.length;i++){
				Packagelist packagelist = new Packagelist();
				packagelist.setPackage_id(Integer.parseInt(idList[i]));
				packagelistDao.delete(packagelist);
			}
			return new ResponseBean(200, "delete success", null);
		}else{
			return new  ResponseBean(500, "delete data error",null);
		}
	}

}
