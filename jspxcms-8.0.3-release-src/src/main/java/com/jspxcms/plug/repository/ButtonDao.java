package com.jspxcms.plug.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.jspxcms.common.orm.Limitable;
import com.jspxcms.plug.domain.Button;
import com.jspxcms.plug.repository.plus.ButtonDaoPlus;

public interface ButtonDao extends Repository<Button, Integer>, ButtonDaoPlus {

	public Page<Button> findAll(Specification<Button> spec, Pageable pageable);

	public List<Button> findAll(Specification<Button> spec, Limitable limitable);

	public Button findOne(Integer id);

	public Button save(Button bean);

	public void delete(Button bean);

	public List<Button> findByLevelAndIsupload(String level, String isupload);

	public List<Button> findByParentidAndIsupload(String parentid, String isupload);

	// --------------------

	@Modifying
	@Query("delete from Button bean where bean.site.id in (?1)")
	public int deleteBySiteId(Collection<Integer> siteIds);

	public int countByParentidAndIsupload(String parentid, String isupload);

	public int countByLevelAndIsupload(String level,String isupload);
}
