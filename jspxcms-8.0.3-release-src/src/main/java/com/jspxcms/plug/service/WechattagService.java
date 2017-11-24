package com.jspxcms.plug.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jspxcms.plug.domain.Wechattag;

public interface WechattagService {
	public Page<Wechattag> findAll(Integer siteId, Map<String, String[]> params,Pageable pageable);

	public Wechattag get(Integer id);

	public Wechattag save(Wechattag bean);

	public Wechattag update(Wechattag bean);

	public void delete(Integer id);
}
