package com.jspxcms.plug.service.impl;

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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspxcms.common.orm.Limitable;
import com.jspxcms.common.orm.RowSide;
import com.jspxcms.common.orm.SearchFilter;
import com.jspxcms.plug.domain.Wechatuser;
import com.jspxcms.plug.repository.WechatuserDao;
import com.jspxcms.plug.service.WechatuserService;

@Service
@Transactional(readOnly = true)
public class WechatuserServiceImpl implements WechatuserService {

	private WechatuserDao dao;

	@Autowired
	public void setDao(WechatuserDao dao) {
		this.dao = dao;
	}

	public Page<Wechatuser> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable) {
		return dao.findAll(spec(siteId, params), pageable);
	}
	
	public Page<Wechatuser> findbyisblacklist(Integer siteId, Map<String, String[]> params, Pageable pageable) {
		return dao.findByIsblacklist(spec(siteId, params), pageable);
	}

	private Specification<Wechatuser> spec(final Integer siteId, Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<Wechatuser> fsp = SearchFilter.spec(filters, Wechatuser.class);
		Specification<Wechatuser> sp = new Specification<Wechatuser>() {
			public Predicate toPredicate(Root<Wechatuser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = fsp.toPredicate(root, query, cb);
				if (siteId != null) {
					pred = cb.and(pred, cb.equal(root.get("site").<Integer>get("id"), siteId));
				}
				return pred;
			}
		};
		return sp;
	}

	public RowSide<Wechatuser> findSide(Integer siteId, Map<String, String[]> params, Wechatuser bean, Integer position,
			Sort sort) {
		if (position == null) {
			return new RowSide<Wechatuser>();
		}
		Limitable limit = RowSide.limitable(position, sort);
		List<Wechatuser> list = dao.findAll(spec(siteId, params), limit);
		return RowSide.create(list, bean);
	}

	public Wechatuser get(String openid) {
		return dao.findOne(openid);
	}

	@Transactional
	public Wechatuser save(Wechatuser bean) {
		Wechatuser user = dao.save(bean);
		return user;
	}

	@Transactional
	public Wechatuser update(Wechatuser bean) {
		Wechatuser user = dao.findOne(bean.getOpenid());
		user.setSubcribe(bean.getSubcribe());
		if (user.getSubcribe() == 0)
			return bean;
		user.setSubcribe_time(bean.getSubcribe_time());
		user.setNickname(bean.getNickname());
		user.setRemark(bean.getRemark());
		user.setSex(bean.getSex());
		user.setLanguage(bean.getLanguage());
		user.setCountry(bean.getCountry());
		user.setProvince(bean.getProvince());
		user.setCity(bean.getCity());
		user.setHeadimgurl(bean.getHeadimgurl(0));
		user.setTagid_list(bean.getTagid_list());
		return null;
	}

	@Transactional
	public void delete(String openid) {
		dao.delete(openid);
	}

	@Transactional
	public void delete(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			dao.delete(ids[i]);
		}
	}

}
