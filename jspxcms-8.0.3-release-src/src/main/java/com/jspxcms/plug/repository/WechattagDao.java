package com.jspxcms.plug.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import com.jspxcms.plug.domain.Wechattag;

public interface WechattagDao extends Repository<Wechattag, Integer>{
	
	public Page<Wechattag> findAll(Specification<Wechattag> spec, Pageable pageable);
	
	public Wechattag findOne(Integer id);

	public Wechattag save(Wechattag bean);

	public void delete(Integer id);

}
