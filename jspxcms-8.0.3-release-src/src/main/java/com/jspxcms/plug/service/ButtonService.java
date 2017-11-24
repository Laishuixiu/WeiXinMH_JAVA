package com.jspxcms.plug.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jspxcms.common.orm.Limitable;
import com.jspxcms.common.orm.RowSide;
import com.jspxcms.plug.domain.Button;

public interface ButtonService {
	public Page<Button> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable);

	public RowSide<Button> findSide(Integer siteId, Map<String, String[]> params, Button bean, Integer position,
			Sort sort);

	public List<Button> findList(Integer[] siteId, Limitable limitable);

	public Button get(Integer id);

	public Button save(Button bean, Integer siteId);

	public Button update(Button bean);

	public Button delete(Integer id);

	public Button upload(Integer id);

	public Button cancleupload(Integer id);

	public Button[] delete(Integer[] ids);

	public Button[] upload(Integer[] ids,RedirectAttributes ra);

	public Button[] cancleupload(Integer[] ids);

	public List<com.foxinmy.weixin4j.model.Button> wxmenuupdate();
}
