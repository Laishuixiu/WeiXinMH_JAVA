package com.jspxcms.plug.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.foxinmy.weixin4j.type.ButtonType;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.support.Siteable;

/*
 * 微信菜单按钮
 * 实体类
 * @Author:heyidao
 * */

@Entity
@Table(name = "PLUG_WXMENU_BUTTON", uniqueConstraints = @UniqueConstraint(columnNames = "wxmenu_id"))
public class Button implements Siteable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Site site;
	private String name;
	private String type;
	private String level;
	private String parentid;
	private String key;
	private String url;
	private String isupload;
	private String editor;
	private Date operatingtime;
	private Date uploadtime;
	private String isdelete;
	private List<Button> subbuttons;

	@Id
	@Column(name = "wxmenu_id", unique = true, nullable = false)
	@TableGenerator(name = "tg_plug_wxmenu_button", pkColumnValue = "plug_wxmenu_button", initialValue = 1, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_plug_wxmenu_button")
	public Integer getId() {
		return id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wxmenu_siteid", nullable = false)
	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Column(name = "wxmenu_name", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "wxmenu_type", nullable = false)
	public String getType() {
		return type;
	}

	@Column(name = "wxmenu_level", nullable = false)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "wxmenu_parentid", nullable = true)
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "wxmenu_key", nullable = true)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "wxmenu_url", nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "wxmenu_editor", nullable = false)
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "wxmenu_isupload", nullable = false)
	public String getIsupload() {
		return isupload;
	}

	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "wxmenu_operatingtime", nullable = false)
	public Date getOperatingtime() {
		return operatingtime;
	}

	public void setOperatingtime(Date operatingtime) {
		this.operatingtime = operatingtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "wxmenu_uploadtime", nullable = true)
	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	@Column(name = "wxmenu_isdelete", nullable = false)
	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	@Transient
	public List<Button> getSubbuttons() {
		return subbuttons;
	}

	public void setSubbuttons(List<Button> subbuttons) {
		this.subbuttons = subbuttons;
	}

	
}
