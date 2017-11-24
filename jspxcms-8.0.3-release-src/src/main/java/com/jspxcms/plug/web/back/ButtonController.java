package com.jspxcms.plug.web.back;

import static com.jspxcms.core.constant.Constants.CANCLEUPLOAD_SUCCESSS;
import static com.jspxcms.core.constant.Constants.CREATE;
import static com.jspxcms.core.constant.Constants.DELETE_SUCCESS;
import static com.jspxcms.core.constant.Constants.EDIT;
import static com.jspxcms.core.constant.Constants.MESSAGE;
import static com.jspxcms.core.constant.Constants.OPRT;
import static com.jspxcms.core.constant.Constants.SAVE_SUCCESS;
import static com.jspxcms.core.constant.Constants.UPLOAD_SUCCESS;
import static com.jspxcms.core.constant.Constants.WECHAT_SUCCESS;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.jspxcms.common.orm.RowSide;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.constant.Constants;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.service.OperationLogService;
import com.jspxcms.core.support.Backends;
import com.jspxcms.core.support.Context;
import com.jspxcms.plug.domain.Button;
import com.jspxcms.plug.service.ButtonService;

@Controller
@RequestMapping("/plug/wxmenu")
public class ButtonController {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@RequiresPermissions("plug:wxmenu:list2")
	@RequestMapping("list2.do")
	public String list(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Integer siteId = Context.getCurrentSiteId();
		Map<String, String[]> params = Servlets.getParamValuesMap(request, Constants.SEARCH_PREFIX);
		String[] a = { "0" };
		params.put("CONTAIN_isdelete", a);
		Page<Button> pagedList = service.findAll(siteId, params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		try {
			List<com.foxinmy.weixin4j.model.Button> buttonList = wechat.getMenu();
			for (com.foxinmy.weixin4j.model.Button button: buttonList
					) {
				System.out.println(button.toString());
			}
		} catch (Exception e){
			System.out.println(e);
		}


		System.out.println(wechat.getWeixinAccount());
		return "plug/wxmenu/wxmenu_list";
	}

	@RequiresPermissions("plug:wxmenu:create")
	@RequestMapping("create2.do")
	public String create(Integer id, org.springframework.ui.Model modelMap) {
		Site site = Context.getCurrentSite();
		if (id != null) {
			Button bean = service.get(id);
			Backends.validateDataInSite(bean, site.getId());
			modelMap.addAttribute("bean", bean);
		}
		modelMap.addAttribute(OPRT, CREATE);
		return "plug/wxmenu/wxmenu_form";
	}

	@RequiresPermissions("plug:wxmenu:edit")
	@RequestMapping("edit2.do")
	public String edit(Integer id, Integer position,
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable, HttpServletRequest request,
			org.springframework.ui.Model modelMap) {
		Integer siteId = Context.getCurrentSiteId();
		Button bean = service.get(id);
		Backends.validateDataInSite(bean, siteId);
		Map<String, String[]> params = Servlets.getParamValuesMap(request, Constants.SEARCH_PREFIX);
		RowSide<Button> side = service.findSide(siteId, params, bean, position, pageable.getSort());
		modelMap.addAttribute("bean", bean);
		modelMap.addAttribute("site", side);
		modelMap.addAttribute("position", position);
		modelMap.addAttribute(OPRT, EDIT);
		return "plug/wxmenu/wxmenu_form";
	}

	@RequiresPermissions("plug:wxmenu:save")
	@RequestMapping("save2.do")
	public String save(@Valid Button bean, String redirect, HttpServletRequest request, RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId();
		service.save(bean, siteId);
		logService.operation("opr.wxmenu.add", bean.getName(), null, bean.getId(), request);
		logger.info("save Button, name={}.", bean.getName());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else if (Constants.REDIRECT_CREATE.equals(redirect)) {
			return "redirect:create.do";
		} else {
			ra.addAttribute("id", bean.getId());
			return "redirect:edit.do";
		}
	}

	@RequiresPermissions("plug:wxmenu:update")
	@RequestMapping("update2.do")
	public String update(@ModelAttribute("bean") Button bean, Integer position, String redirect,
			HttpServletRequest request, RedirectAttributes ra) {
		Site site = Context.getCurrentSite();
		Backends.validateDataInSite(bean, site.getId());
		service.update(bean);
		logService.operation("opr.wxmenu.edit", bean.getName(), null, bean.getId(), request);
		logger.info("update Button, name={}.", bean.getName());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else {
			ra.addAttribute("id", bean.getId());
			ra.addAttribute("position", position);
			return "redirect:edit.do";
		}
	}

	@RequiresPermissions("plug:wxmenu:delete")
	@RequestMapping("delete2.do")
	public String delete(Integer[] ids, HttpServletRequest request, RedirectAttributes ra) {
		Site site = Context.getCurrentSite();
		validateIds(ids, site.getId());
		Button[] beans = service.delete(ids);
		for (Button bean : beans) {
			logService.operation("opr.wxmenu.delete", bean.getName(), null, bean.getId(), request);
			logger.info("delete Button, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, DELETE_SUCCESS);
		return "redirect:list.do";
	}

	@RequiresPermissions("plug:wxmenu:upload")
	@RequestMapping("upload2.do")
	public String upload(Integer[] ids, HttpServletRequest request, RedirectAttributes ra) {
		Site site = Context.getCurrentSite();
		validateIds(ids, site.getId());
		Button[] beans = service.upload(ids, ra);
		if (beans == null) {
			return "redirect:list.do";
		}
		for (Button bean : beans) {
			logService.operation("opr.wxmenu.upload", bean.getName(), null, bean.getId(), request);
			logger.info("upload Button, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, UPLOAD_SUCCESS);
		return "redirect:list.do";
	}

	@RequiresPermissions("plug:wxmenu:cancleupload")
	@RequestMapping("cancleupload2.do")
	public String cancleupload(Integer[] ids, HttpServletRequest request, RedirectAttributes ra) {
		Site site = Context.getCurrentSite();
		validateIds(ids, site.getId());
		Button[] beans = service.cancleupload(ids);
		for (Button bean : beans) {
			logService.operation("opr.wxmenu.cancleupload", bean.getName(), null, bean.getId(), request);
			logger.info("upload Button, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, CANCLEUPLOAD_SUCCESSS);
		return "redirect:list.do";
	}

	@RequiresPermissions("plug:wxmenu:reload")
	@RequestMapping("reload2.do")
	public String reload(String redirect, HttpServletRequest request, RedirectAttributes ra) {
		try {
			ApiResult result = wechat.createMenu(service.wxmenuupdate());
			if (result.getReturnCode().equals("0")) {
				ra.addFlashAttribute(MESSAGE, WECHAT_SUCCESS);
			} else {
				ra.addFlashAttribute(MESSAGE, "更新失败，错误信息为：" + result.getReturnMsg());
			}
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		return "redirect:list.do";
	}

	@ModelAttribute("bean")
	public Button preloadBean(@RequestParam(required = false) Integer oid) {
		return oid != null ? service.get(oid) : null;
	}

	private void validateIds(Integer[] ids, Integer siteId) {
		for (Integer id : ids) {
			Backends.validateDataInSite(service.get(id), siteId);
		}
	}

	@Autowired
	private WeixinProxy wechat;
	@Autowired
	private OperationLogService logService;
	@Autowired
	private ButtonService service;
}
