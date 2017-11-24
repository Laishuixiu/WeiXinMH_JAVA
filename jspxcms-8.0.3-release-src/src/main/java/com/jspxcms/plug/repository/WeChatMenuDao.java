package com.jspxcms.plug.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jspxcms.plug.domain.WeChatMenu;


/**
 * 微信菜单管理  Dao
 * @author laishuixiu
 *
 */
public interface WeChatMenuDao extends JpaRepository<WeChatMenu, Integer> {	
	
	/**
	 * 多条件分页查询
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<WeChatMenu> findAll(Specification<WeChatMenu> spec, Pageable pageable);	
	
	@Query("select m from WeChatMenu m where m.site.id=:siteId and m.usingState=:usingState") 
	public List<WeChatMenu> findAllByUsingState(@Param("siteId")Integer siteId,@Param("usingState")String usingState);
	
	/**
	 * 根据parentId查询
	 * @param parentId
	 * @return
	 */
	@Query("select m from WeChatMenu m where m.parent.id = :pid") 
	public List<WeChatMenu> findByPid(@Param("pid") Integer parentId);	
	
	/**
	 * 查询一级菜单
	 * @param siteId
	 * @return
	 */
	@Query("select m from WeChatMenu m where m.site.id = :siteId") 
	public List<WeChatMenu> findLevelOne(@Param("siteId")Integer siteId);
	
	/**
	 * 查询已启动的一级菜单
	 * @return
	 */	
	public long countBylevelAndUsingState(String level, String usingState);
	
	/**
	 * 查询已启动的二级菜单
	 * @return
	 */
	@Query("select count(1) from WeChatMenu m where m.parent.id=:pid and m.usingState='1'") 
	public long countByPid(@Param("pid")Integer pid);
	
	
		
}
