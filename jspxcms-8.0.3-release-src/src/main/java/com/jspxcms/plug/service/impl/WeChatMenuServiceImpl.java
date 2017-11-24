package com.jspxcms.plug.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspxcms.common.orm.SearchFilter;
import com.jspxcms.core.repository.SiteDao;
import com.jspxcms.plug.domain.WeChatMenu;
import com.jspxcms.plug.myException.myException;
import com.jspxcms.plug.repository.WeChatMenuDao;
import com.jspxcms.plug.service.WeChatMenuService;


/**
 * 微信菜单管理 ServiceImpl
 * @author laishuixiu
 *
 */
@Service
public class WeChatMenuServiceImpl implements WeChatMenuService {
	
	@Autowired
	public WeChatMenuDao dao;
	
	@Autowired
	public SiteDao siteDao;
	
	/**
	 * 根据Id查询
	 */
	public WeChatMenu findById(Integer id) {
		return dao.findOne(id);
	}
	
	/**
	 * 保存
	 */
	@Transactional
	public WeChatMenu save(WeChatMenu bean, Integer parentId) {
		if(parentId != null){
			bean.setParent(findById(parentId));
		}
		
		return dao.save(bean);
	}
	
	/**
	 * 查询所有已启动的菜单
	 */
	public List<WeChatMenu> findByUsingState(Integer siteId, String usingState){
		return dao.findAllByUsingState(siteId, usingState);
	}
	
	/**
	 * 根据父Id查找数据
	 */
	@Override
//	public List<WeChatMenu> findByParentId(Integer parentId) {		
//		return dao.findByPid(parentId);
//	}
	
	/**
	 * 查询一级菜单
	 */
	public List<WeChatMenu> findLevelOne(Integer siteId){		
		return dao.findLevelOne(siteId);		
	}
	
	/**
	 * 分页查询
	 */
	public Page<WeChatMenu> findPage(Integer siteId, Map<String, String[]> params, Pageable pageable) {		
		return dao.findAll(spec(siteId, params), pageable);
	}
		
	/**
	 * 组装分页查询条件
	 * @param siteId
	 * @param params
	 * @return
	 */
	private Specification<WeChatMenu> spec(final Integer siteId, Map<String, String[]> params){
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<WeChatMenu> fsp = SearchFilter.spec(filters, WeChatMenu.class);
		Specification<WeChatMenu> sp = new Specification<WeChatMenu>() {
			public Predicate toPredicate(Root<WeChatMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = fsp.toPredicate(root, query, cb);
				if (siteId != null) {
					pred = cb.and(pred, cb.equal(root.get("site")
							.<Integer> get("id"), siteId));
				}
				
				return pred;
			}
		};
		return sp;
	}
	
	private WeChatMenu doDelete(Integer id) {
		WeChatMenu bean = dao.findOne(id);
		if (bean != null) {			
			dao.delete(bean);
		}
		return bean;
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public WeChatMenu delete(Integer id) {
		WeChatMenu bean = doDelete(id);
		return bean;
	}
	
	/**
	 * 批量删除
	 */
	@Transactional
	public WeChatMenu[] delete(Integer[] ids) {
		int len = ids.length;
		List<WeChatMenu> list = new ArrayList<WeChatMenu>(len);
		WeChatMenu bean;
		for (Integer id : ids) {
			bean = doDelete(id);
			if (bean != null) {
				list.add(bean);
			}
		}
		return list.toArray(new WeChatMenu[list.size()]);
	}
	
	
	private WeChatMenu doEnable(Integer id){
		WeChatMenu bean = dao.findOne(id);
		long count = 0;
		if(bean.getParent() != null){	//二级
			count = dao.countByPid(bean.getParent().getId());
			System.out.println("二级：" + count);
			if(count >= (long)5){
				throw new myException("温馨提示：每个一级菜单下，最多只能启用5个按钮！");
			}
		} else {
			count = dao.countBylevelAndUsingState(bean.getLevel(), "1");
			System.out.println("一级：" + count);
			if(count >= (long)3){
				throw new myException("温馨提示：一级菜单最多同时只能启用3个按钮！");
			}
		}
		
		bean.setUsingState("1");
		bean  = dao.save(bean);
		return bean;
	}
	
	/**
	 * 启用
	 */
	@Transactional
	public WeChatMenu enable(Integer id) {		
		WeChatMenu bean = doEnable(id);
		return bean;
		
	}

	/**
	 * 批量启用
	 */
	@Transactional
	public WeChatMenu[] enable(Integer[] ids) {
		int len = ids.length;
		List<WeChatMenu> list = new ArrayList<WeChatMenu>(len);
		WeChatMenu bean;
		for (Integer id : ids) {
			bean = doEnable(id);
			if (bean != null) {
				list.add(bean);
			}
		}
		return list.toArray(new WeChatMenu[list.size()]);		
	}
	
	
	private WeChatMenu doCancleEnable(Integer id) {
		WeChatMenu bean = dao.findOne(id);
		bean.setUsingState("0");
		bean = dao.save(bean);
		return bean;
	}
	
	/**
	 * 取消启用
	 */
	@Transactional
	public WeChatMenu cancleEnable(Integer id) {
		WeChatMenu bean = doCancleEnable(id);
		return bean;
	}
	
	/**
	 * 批量取消启动
	 */
	@Transactional
	public WeChatMenu[] cancleEnable(Integer[] ids) {
		int len = ids.length;
		List<WeChatMenu> list = new ArrayList<WeChatMenu>(len);
		WeChatMenu bean;
		for (Integer id : ids) {
			bean = doCancleEnable(id);
			if (bean != null) {
				list.add(bean);
			}
		}
		return list.toArray(new WeChatMenu[list.size()]);	
	}	

}
