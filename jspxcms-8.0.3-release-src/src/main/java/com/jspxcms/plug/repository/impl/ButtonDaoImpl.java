package com.jspxcms.plug.repository.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;

import com.jspxcms.common.orm.JpqlBuilder;
import com.jspxcms.common.orm.Limitable;
import com.jspxcms.plug.domain.Button;
import com.jspxcms.plug.repository.plus.ButtonDaoPlus;

public class ButtonDaoImpl implements ButtonDaoPlus {
	@SuppressWarnings("unchecked")
	public List<Button> getList(Integer[] siteId, Limitable limitable) {
		JpqlBuilder jpql = new JpqlBuilder();
		jpql.append("from Button bean where 1=1");
		if (ArrayUtils.isNotEmpty(siteId)) {
			jpql.append(" and bean.wxmenu.siteid in (:siteId)");
			jpql.setParameter("siteId", Arrays.asList(siteId));
		}
		return jpql.list(em, limitable);
	}

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
