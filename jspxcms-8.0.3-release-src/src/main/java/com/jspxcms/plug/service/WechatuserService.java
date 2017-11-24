package com.jspxcms.plug.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jspxcms.common.orm.RowSide;
import com.jspxcms.plug.domain.Wechatuser;

public interface WechatuserService {
	public Page<Wechatuser> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable);

	public RowSide<Wechatuser> findSide(Integer siteId, Map<String, String[]> params, Wechatuser bean, Integer position,
			Sort sort);

	public Wechatuser get(String openid);

	public Wechatuser save(Wechatuser bean);

	public Wechatuser update(Wechatuser bean);

	public void delete(String openid);

	public void delete(String[] ids);
}
