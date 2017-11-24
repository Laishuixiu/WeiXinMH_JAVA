package com.jspxcms.plug.web.back;

import static com.jspxcms.core.constant.Constants.MESSAGE;
import static com.jspxcms.core.constant.Constants.OPRT;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.model.Tag;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.constant.Constants;
import com.jspxcms.plug.domain.Wechattag;
import com.jspxcms.plug.domain.Wechatuser;
import com.jspxcms.plug.service.WechattagService;
import com.jspxcms.plug.service.WechatuserService;

@Controller
@RequestMapping("/plug/wechatuser")
public class WechatUserController {

	@Autowired
	private WechatuserService service;

	@Autowired
	private WechattagService tagservice;

	@Autowired
	private WeixinProxy wp;

	@RequiresPermissions("plug:wechatuser:list")
	@RequestMapping("list.do")
	public String list(Pageable pageable,HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Map<String, String[]> params = Servlets.getParamValuesMap(request, Constants.SEARCH_PREFIX);
		Page<Wechatuser> pagedList = service.findAll(null, params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		modelMap.addAttribute(OPRT, "list");
		Page<Wechattag> tagList = tagservice.findAll(null, params, pageable);
		modelMap.addAttribute("tagList", tagList);
		return "plug/wechatuser/wechatuser_list";
	}

	@RequiresPermissions("plug:wechatuser:blacklist")
	@RequestMapping("blacklist.do")
	public String blacklist(@PageableDefault(sort = "openid", direction = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Map<String, String[]> params = Servlets.getParamValuesMap(request, Constants.SEARCH_PREFIX);
		Page<Wechatuser> pagedList = service.findAll(null, params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		modelMap.addAttribute(OPRT, "blacklist");
		return "plug/wechatuser/wechatuser_list";
	}

	@RequiresPermissions("plug:wechatuser:createtag")
	@RequestMapping("createtag.do")
	public String createtag(HttpServletRequest request, org.springframework.ui.Model modelMap, RedirectAttributes ra) {
		try {
			String tagname = request.getParameter("tagname");
			Tag tag = wp.createTag(tagname);
			tag.getId();
			tag.getName();
			Wechattag bean = new Wechattag();
			bean.applyDefaultValue();
			bean.setId(tag.getId());
			bean.setName(tag.getName());
			tagservice.save(bean);
		} catch (WeixinException e) {
			String errcode = e.getErrorCode();
			switch (errcode) {
			case "-1":
				ra.addFlashAttribute(MESSAGE, e.getErrorDesc());
				break;
			case "45157":
				ra.addFlashAttribute(MESSAGE, e.getErrorDesc());
				break;
			case "45158":
				ra.addFlashAttribute(MESSAGE, e.getErrorDesc());
				break;
			case "45056":
				ra.addFlashAttribute(MESSAGE, e.getErrorDesc());
				break;
			default:
				ra.addFlashAttribute(MESSAGE, e.getErrorDesc());
				break;
			}
		}
		return "redirect:list.do";
	}

	@RequiresPermissions("plug:wechatuser:addblacklist")
	@RequestMapping("addblacklist.do")
	public String addblacklist(String[] openids, HttpServletRequest request, org.springframework.ui.Model modelMap) {
		return "plug/wxmenu/wxmenu_list";
	}

	@RequiresPermissions("plug:wechatuser:taglist")
	@RequestMapping("taglist.do")
	public String taglist(HttpServletRequest request, org.springframework.ui.Model modelMap) {
		String id=request.getParameter("tagid");
		Wechattag tag=tagservice.get(Integer.valueOf(id));
		modelMap.addAttribute("tagname",tag.getName());
		return "redirect:list.do";
	}
	
}
