package com.jspxcms.plug.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jspxcms.plug.domain.WeChatMenu;

/**
 * 微信菜单管理 Service
 * @author laishuixiu
 *
 */
public interface WeChatMenuService {	
	public WeChatMenu findById(Integer id);
    public WeChatMenu save(WeChatMenu bean, Integer parentId);
    //public List<WeChatMenu> findByParentId(Integer parentId); 
    public List<WeChatMenu> findByUsingState(Integer siteId, String usingState);
    public List<WeChatMenu> findLevelOne(Integer siteId);  
    public Page<WeChatMenu> findPage(Integer siteId,Map<String, String[]> params,Pageable pageable);
    public WeChatMenu delete(Integer id); 
    public WeChatMenu[] delete(Integer[] ids); 
    public WeChatMenu enable(Integer id);
    public WeChatMenu[] enable(Integer[] ids);
    public WeChatMenu cancleEnable(Integer id);
    public WeChatMenu[] cancleEnable(Integer[] ids);    
}
