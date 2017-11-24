package com.jspxcms.plug.web.back;

import static com.jspxcms.core.constant.Constants.CREATE;
import static com.jspxcms.core.constant.Constants.MESSAGE;
import static com.jspxcms.core.constant.Constants.OPRT;
import static com.jspxcms.core.constant.Constants.SAVE_SUCCESS;
import static com.jspxcms.core.constant.Constants.OPERATION_SUCCESS;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.AbstractDocument.Content;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.taglibs.standard.tei.ForEachTEI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foxinmy.weixin4j.util.Weixin4jConfigUtil;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.constant.Constants;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.domain.User;
import com.jspxcms.core.service.OperationLogService;
import com.jspxcms.core.service.UserService;
import com.jspxcms.core.support.Context;
import com.jspxcms.plug.domain.WeChatMenu;
import com.jspxcms.plug.myException.myException;
import com.jspxcms.plug.service.WeChatMenuService;

/**
 * 微信菜单管理 Controller
 * @author laishuixiu
 *
 */
@Controller
@RequestMapping("/plug/wxmenu")
public class WeChatMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeChatMenuController.class);

	
	
	@Autowired
	private OperationLogService logService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WeChatMenuService service;
	
	/**
	 * 分页条件查询
	 * @param pageable
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:list")
	@RequestMapping("list.do")
	public String list(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable,
			HttpServletRequest request, Model model) {
		Site site = Context.getCurrentSite();
		Integer siteId = site.getId();		
		Map<String, String[]> params = Servlets.getParamValuesMap(request, Constants.SEARCH_PREFIX);
		System.out.println(params);
		Page<WeChatMenu> pagedList = service.findPage(siteId, params, pageable);
		model.addAttribute("pagedList", pagedList);
		return "plug/wxmenu/wxmenu_list";
	}
	
	/**
	 * 打开表单页面
	 * @param id
	 * @param oprt	操作（create 或 edit）
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"plug:wxmenu:create","plug:wxmenu:edit"},logical=Logical.OR)
	@RequestMapping("form.do")
	public String edit(Integer id,String oprt, Integer position, Model model){
		if(id != null){
			WeChatMenu menu = service.findById(id);
			model.addAttribute("bean", menu);	
			model.addAttribute("position", position);
		}
		List<WeChatMenu> levelOneMenu = service.findLevelOne(Context.getCurrentSiteId());
		model.addAttribute("menuList", levelOneMenu);
		model.addAttribute(Constants.OPRT, oprt);
		return "plug/wxmenu/wxmenu_form";
	}
	
	/**
	 * 打开表单页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	/*
	@RequiresPermissions(value={"plug:wxmenu:create","plug:wxmenu:edit"},logical=Logical.OR)
	@RequestMapping("edit.do")
	public ModelAndView edit1(String id) {
		WeChatMenu menu = service.findById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("plug/wxmenu/wxmenu_form");
		mv.addObject("wxMenu", menu);
		return mv;
	}*/
		
	
	/**
	 * 新增
	 * @param id
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:create")
	@RequestMapping("create.do")
	public String create(Model model){
		model.addAttribute(OPRT, CREATE);	//记录操作
		return "plug/wxmenu/wxmenu_form";		
	}
	
	/**
	 * 保存
	 * @param bean
	 * @param parentId
	 * @param redirect
	 * @param request
	 * @param ra
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:save")
	@RequestMapping("save.do")
	public String save(WeChatMenu bean, Integer parentId, Integer operatorId, Integer updateOperatorId,String redirect ,HttpServletRequest request, RedirectAttributes ra){
		User user = Context.getCurrentUser();
		if(bean.getId() != null){	//修改
			bean.setOperator(userService.get(operatorId));
			
			bean.setUpdateOperator(user);
			bean.setUpdateDate(new Date());
		} else {	//新增
			bean.setOperator(user);
			bean.setCreateDate(new Date());
			
			bean.setUpdateOperator(user);
			bean.setUpdateDate(new Date());
		}		
		bean.setSite(Context.getCurrentSite());
		service.save(bean, parentId);
		
		String operation = "";
		if(bean.getId() != null){	//修改
			operation = "opr.wxmenu.update";
		}else{	//新增
			operation = "opr.wxmenu.add";
		}
		
		String ip = Servlets.getRemoteAddr(request);
		logService.operation(operation, bean.getName(), null, bean.getId(), ip, user.getId(), Context.getCurrentSiteId());
		logger.info("save Button, name={}.", bean.getName());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {	//保存并返回
			return "redirect:list.do";
		} else if (Constants.REDIRECT_CREATE.equals(redirect)) { //保存并新增
			return "redirect:form.do";
		} else {
			ra.addAttribute("id", bean.getId());
			return "redirect:form.do";
		}
	}
	
	/**
	 * 启用菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:enable")
	@RequestMapping("enable.do")
	public String enable(Integer[] ids, RedirectAttributes ra, HttpServletRequest request){
		try {
			WeChatMenu[] beans = service.enable(ids);
			for (WeChatMenu bean : beans) {
				logService.operation("opr.wxmenu.enable", bean.getName(), null, bean.getId(), request);
				logger.info("enable Button, name={}.", bean.getName());
			}			
		} catch (myException e) {
			System.out.println(e.getMessage());
			ra.addFlashAttribute(MESSAGE, e.getMessage());
			return "redirect:list.do";
		}
		
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}
	
	/**
	 * 取消启用菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:cancleEnable")
	@RequestMapping("cancleEnable.do")
	public String cancleEnable(Integer[] ids,RedirectAttributes ra,HttpServletRequest request){		
		WeChatMenu[] beans = service.cancleEnable(ids);
		for (WeChatMenu bean : beans) {
			logService.operation("opr.wxmenu.cancleEnable", bean.getName(), null, bean.getId(), request);
			logger.info("cancleEnable Button, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}
	
	/**
	 * 删除数据
	 * @param ids
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:delete")
	@RequestMapping("delete.do")
	public String delete(Integer[] ids,RedirectAttributes ra,HttpServletRequest request){
		WeChatMenu[] beans = service.delete(ids);
		for (WeChatMenu bean : beans) {
			logService.operation("opr.wxmenu.cancleEnable", bean.getName(), null, bean.getId(), request);
			logger.info("cancleEnable Button, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		
		return "redirect:list.do";
	}
	
	/**
	 * 创建自定义菜单
	 * @param ra
	 * @return
	 */
	@RequiresPermissions("plug:wxmenu:createMenu")
	@RequestMapping("createMenu.do")
	public String createMenu(RedirectAttributes ra){
		//创建菜单
	       StringBuffer json = new StringBuffer();
	       json.append("{")
	       		.append("	\"button\":[ ")
	       		.append("	}	")
	       		.append("			\"type\":\"click\", ")
	       		.append("			\"name\":\"今日歌曲\",	")
	       		.append("			\"key\":\"V1001_TODAY_MUSIC\"	")
	       		.append(" 	},	")
	       		.append("	{	")
	       		.append("		 \"name\":\"菜单\",	")
	       		.append("		\"sub_button\":[	")
	       		.append("		{	")
	       		.append("			\"type\":\"view\",	")
	       		.append("			\"name\":\"搜索\",	")
	       		.append("			\"url\":\"http://www.soso.com/\"	")
	       		.append("		},")       		
	       		.append("		{")
	       		.append("		 	\"type\":\"view\",	")
	       		.append("		 	\"name\":\"视频\",	")
	       		.append("			\"url\":\"http://v.qq.com/\"	")
	       		.append("	 	},")
	       		.append("	 	{")
	       		.append("			\"type\":\"click\",	")
	       		.append("			\"name\":\"赞一下我们\",	")
	       		.append("			\"key\":\"V1001_GOOD\"	")
	       		.append("		}]	")
	       		.append("	 }]	")
	       		.append(" }");
	       
	     List<WeChatMenu> list = service.findLevelOne(Context.getCurrentSiteId());
	       
	     for (WeChatMenu m : list) {
			System.out.println("菜单：" + m.getName() + "=" + m.getSubMenu().size());
		}
		
		return "redirect:list.do";
	}
	
}
