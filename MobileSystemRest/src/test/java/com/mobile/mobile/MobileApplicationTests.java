package com.mobile.mobile;

import com.mobile.mobile.manager.BaseFunctionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MobileApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private BaseFunctionService baseFunctionService;

	@Test
	public void test(){
		List<Map<String, Object>> list = baseFunctionService.getSqlResult("select * from user");
		list.forEach(item->System.out.println(item.get("user_id")));
	}
}
