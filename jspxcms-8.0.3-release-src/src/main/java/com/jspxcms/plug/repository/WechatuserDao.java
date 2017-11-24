package com.jspxcms.plug.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import com.jspxcms.common.orm.Limitable;
import com.jspxcms.plug.domain.Wechatuser;

public interface WechatuserDao extends Repository<Wechatuser, String> {
	public Page<Wechatuser> findAll(Specification<Wechatuser> spec, Pageable pageable);
	
	public Page<Wechatuser> findByIsblacklist(Specification<Wechatuser> spec, Pageable pageable);

	public List<Wechatuser> findAll(Specification<Wechatuser> spec, Limitable limitable);

	public Wechatuser findOne(String openid);

	public Wechatuser save(Wechatuser bean);

	public void delete(String openid);

}
