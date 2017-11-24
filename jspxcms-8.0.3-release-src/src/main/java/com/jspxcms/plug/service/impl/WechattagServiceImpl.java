package com.jspxcms.plug.service.impl;

import java.util.Collection;
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
import com.jspxcms.plug.domain.Wechattag;
import com.jspxcms.plug.repository.WechattagDao;
import com.jspxcms.plug.service.WechattagService;

@Service
@Transactional(readOnly = true)
public class WechattagServiceImpl implements WechattagService {

	@Autowired
	private WechattagDao dao;

	public Page<Wechattag> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable) {
		return dao.findAll(spec(siteId, params), pageable);
	}

	private Specification<Wechattag> spec(final Integer siteId, Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<Wechattag> fsp = SearchFilter.spec(filters, Wechattag.class);
		Specification<Wechattag> sp = new Specification<Wechattag>() {
			public Predicate toPredicate(Root<Wechattag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = fsp.toPredicate(root, query, cb);
				if (siteId != null) {
					pred = cb.and(pred, cb.equal(root.get("site").<Integer>get("id"), siteId));
				}
				return pred;
			}
		};
		return sp;
	}

	public Wechattag get(Integer id) {
		return dao.findOne(id);
	}

	@Transactional
	public Wechattag save(Wechattag bean) {
		bean.applyDefaultValue();
		Wechattag tag = dao.save(bean);
		return tag;
	}

	@Transactional
	public Wechattag update(Wechattag bean) {
		Wechattag tag = new Wechattag();
		tag.setId(bean.getId());
		tag.setName(bean.getName());
		tag.setCount(bean.getCount());
		dao.save(tag);
		return tag;
	}

	@Transactional
	public void delete(Integer id) {
		dao.delete(id);
	}

}
