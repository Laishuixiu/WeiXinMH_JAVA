package com.jspxcms.plug.web.back;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jspxcms.plug.domain.WeChatMenu;
import com.jspxcms.plug.service.WeChatMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatMenuControllerTest {
	
	@Autowired
	private WeChatMenuService service;

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
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

}
