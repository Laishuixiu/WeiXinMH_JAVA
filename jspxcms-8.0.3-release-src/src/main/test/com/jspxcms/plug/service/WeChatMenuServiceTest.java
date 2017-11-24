package com.jspxcms.plug.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jspxcms.core.service.OperationLogService;
import com.jspxcms.plug.domain.WeChatMenu;
import com.jspxcms.plug.repository.WeChatMenuDao;
import com.jspxcms.plug.web.back.WeChatMenuController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WeChatMenuController.class)
public class WeChatMenuServiceTest {
	@Autowired
	private WeChatMenuService service;
	
	@Autowired
	private OperationLogService logService;

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		WeChatMenu bean = new WeChatMenu();
		bean.setCreateDate(new Date());
		bean.setKey("服务指南");
		bean.setLevel("1");
		bean.setName("服务指南");
		bean.setType("click");
		service.save(bean,null);
	}

	@Test
	public void testFindPage() {
		fail("Not yet implemented");
	}

}
