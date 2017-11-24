package com.jspxcms.plug.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.type.ButtonType;
import com.jspxcms.common.orm.Limitable;
import com.jspxcms.common.orm.RowSide;
import com.jspxcms.common.orm.SearchFilter;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.listener.SiteDeleteListener;
import com.jspxcms.core.service.SiteService;
import com.jspxcms.core.support.Context;
import com.jspxcms.plug.domain.Button;
import com.jspxcms.plug.repository.ButtonDao;
import com.jspxcms.plug.service.ButtonService;

import static com.jspxcms.core.constant.Constants.UPLOAD_FAILURE_1;
import static com.jspxcms.core.constant.Constants.UPLOAD_FAILURE_2;
import static com.jspxcms.core.constant.Constants.UPLOAD_FAILURE_3;
import static com.jspxcms.core.constant.Constants.MESSAGE;

@Service
@Transactional(readOnly = true)
public class ButtonServiceImpl implements ButtonService, SiteDeleteListener {

	public Page<Button> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable) {
		return dao.findAll(spec(siteId, params), pageable);
	}

	public RowSide<Button> findSide(Integer siteId, Map<String, String[]> params, Button bean, Integer position,
			Sort sort) {
		if (position == null) { 
			return new RowSide<Button>();
		}
		Limitable limit = RowSide.limitable(position, sort);
		List<Button> list = dao.findAll(spec(siteId, params), limit);
		return RowSide.create(list, bean);
	}

	private Specification<Button> spec(final Integer siteId, Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<Button> fsp = SearchFilter.spec(filters, Button.class);
		Specification<Button> sp = new Specification<Button>() {
			public Predicate toPredicate(Root<Button> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = fsp.toPredicate(root, query, cb);
				if (siteId != null) {
					pred = cb.and(pred, cb.equal(root.get("site").<Integer>get("id"), siteId));
				}
				return pred;
			}
		};
		return sp;
	}

	public List<Button> findList(Integer[] siteId, Limitable limitable) {
		return dao.getList(siteId, limitable);
	}

	public Button get(Integer id) {
		return dao.findOne(id);
	}

	@Transactional
	public Button save(Button bean, Integer siteId) {
		Site site = siteService.get(siteId);
		bean.setSite(site);
		bean.setIsdelete("0");
		bean.setIsupload("0");
		bean.setOperatingtime(new Date());
		bean.setEditor(Context.getCurrentUser().getUsername());
		bean = dao.save(bean);
		return bean;
	}

	@Transactional
	public Button update(Button bean) {
		bean.setOperatingtime(new Date());
		bean.setEditor(Context.getCurrentUser().getUsername());
		bean = dao.save(bean);
		return bean;
	}

	@Transactional
	public Button delete(Integer id) {
		Button entity = dao.findOne(id);
		entity.setIsdelete("1");
		entity.setIsupload("0");
		entity.setEditor(Context.getCurrentUser().getUsername());
		entity.setOperatingtime(new Date());
		entity.setUploadtime(null);
		dao.save(entity);
		return entity;
	}

	@Transactional
	public Button[] delete(Integer[] ids) {
		Button[] beans = new Button[ids.length];
		for (int i = 0; i < ids.length; i++) {
			beans[i] = delete(ids[i]);
		}
		return beans;
	}

	@Transactional
	public Button upload(Integer id) {
		Button bean = dao.findOne(id);
		bean.setIsupload("1");
		bean.setOperatingtime(new Date());
		bean.setUploadtime(new Date());
		bean.setEditor(Context.getCurrentUser().getUsername());
		return bean;
	}

	@Transactional
	public Button[] upload(Integer[] ids, RedirectAttributes ra) {
		Button[] beans = new Button[ids.length];
		for (int i = 0; i < ids.length; i++) {
			beans[i] = upload(ids[i]);
			String level = beans[i].getLevel();
			if (level.equals("1")) {
				int level1 = dao.countByLevelAndIsupload("1", "1");
				if (level1 > 3) {
					ra.addFlashAttribute(MESSAGE, UPLOAD_FAILURE_1);
					beans[i].setIsupload("0");
					beans[i].setUploadtime(null);
					return null;
				}
			}
			if (level.equals("2")) {
				int level2 = dao.countByParentidAndIsupload(beans[i].getParentid(), "1");
				if (level2 > 5) {
					ra.addFlashAttribute(MESSAGE, UPLOAD_FAILURE_2);
					beans[i].setIsupload("0");
					beans[i].setUploadtime(null);
					return null;
				}
				Integer parentid = Integer.valueOf(beans[i].getParentid());
				Button bean = dao.findOne(parentid);
				String statu = bean.getIsupload();
				if (statu.equals("0")) {
					ra.addFlashAttribute(MESSAGE, UPLOAD_FAILURE_3);
					beans[i].setIsupload("0");
					beans[i].setUploadtime(null);
					return null;
				}
			}

		}
		return beans;
	}

	@Transactional
	public Button cancleupload(Integer id) {
		Button bean = dao.findOne(id);
		bean.setIsupload("0");
		bean.setOperatingtime(new Date());
		bean.setUploadtime(null);
		bean.setEditor(Context.getCurrentUser().getUsername());
		return bean;
	}

	@Transactional
	public Button[] cancleupload(Integer[] ids) {
		Button[] beans = new Button[ids.length];
		for (int i = 0; i < ids.length; i++) {
			beans[i] = cancleupload(ids[i]);
		}
		return beans;
	}

	@Transactional
	public void preSiteDelete(Integer[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			dao.deleteBySiteId(Arrays.asList(ids));
		}
	}

	public List<com.foxinmy.weixin4j.model.Button> wxmenuupdate() {
		List<com.foxinmy.weixin4j.model.Button> result = new ArrayList<>();
		List<Button> level1 = dao.findByLevelAndIsupload("1", "1");
		com.foxinmy.weixin4j.model.Button bean = null;
		for (int i = 0; i < level1.size(); i++) {
			Button obean = level1.get(i);
			String name = obean.getName();
			ButtonType type = ButtonType.valueOf(obean.getType());
			String content = null;
			if (type.name().equals("click")) {
				content = obean.getKey();
			} else if (type.name().equals("view")) {
				content = obean.getUrl();
			}
			String parentid = Integer.toString(obean.getId());
			int number = dao.countByParentidAndIsupload(parentid, "1");
			if (number > 0) {
				List<Button> level2 = dao.findByParentidAndIsupload(parentid, "1");
				com.foxinmy.weixin4j.model.Button[] result2 = new com.foxinmy.weixin4j.model.Button[number];
				for (int j = 0; j < level2.size(); i++) {
					Button subbean = level2.get(i);
					String subname = subbean.getName();
					ButtonType subtype = ButtonType.valueOf(subbean.getType());
					String subcontent = null;
					if (subtype.name().equals("click")) {
						subcontent = subbean.getKey();
					} else if (subtype.name().equals("view")) {
						subcontent = subbean.getUrl();
					}
					com.foxinmy.weixin4j.model.Button newsub = new com.foxinmy.weixin4j.model.Button(subname,
							subcontent, subtype);
					result2[j] = newsub;
				}
				bean = new com.foxinmy.weixin4j.model.Button(name, result2);
			} else {
				bean = new com.foxinmy.weixin4j.model.Button(name, content, type);
			}
			result.add(bean);
			bean = null;
		}
		return result;
	}

	private SiteService siteService;

	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	private ButtonDao dao;

	@Autowired
	public void setDao(ButtonDao dao) {
		this.dao = dao;
	}

}
