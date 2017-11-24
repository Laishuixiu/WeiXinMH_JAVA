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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.jspxcms.core.domain.Site;
import com.jspxcms.core.domain.User;

/**
 * 微信菜单实体类
 * @author laishuixiu
 *
 */
@Entity
@Table(name="plug_wechat_menu")
public class WeChatMenu {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	@Column(name="id", unique = true, nullable = false)
	@TableGenerator(name = "tg_plug_wechat_menu", pkColumnValue = "plug_wechat_menu", initialValue = 1, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_plug_wechat_menu")
	private Integer id;	//菜单ID
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id", nullable = false)
	private Site site;	//站点ID	
	
	private String name;	//按钮名称	
	
	private String type;	//类型
	
	private String level;	//菜单等级
		
	//private String parentid;	//父菜单ID
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parentid", nullable = true)
	private WeChatMenu parent;	//父菜单ID
	
	@Column(name="keyword")
	private String key;	// key值
	
	private String url;		//链接地址
	
	//private String operator;	//操作人
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operator", nullable = true)
	private User operator;	//操作人
	
	@Column(name="using_state")
	private String usingState;	//使用状态
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;	//创建时间
	
	//@Column(name="update_operator")
	//private String updateOperator;	//最后操作人
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_operator", nullable = true)
	private User updateOperator;//最后操作人
	
	@Temporal(TemporalType.DATE)
	@Column(name="update_date")
	private Date updateDate;	//修改时间
	
	@Column(name="is_del")
	private String isDel;	//删除标志
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value = "id asc")
	private List<WeChatMenu> subMenu;	//子菜单

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public WeChatMenu getParent() {
		return parent;
	}

	public void setParent(WeChatMenu parent) {
		this.parent = parent;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getUsingState() {
		return usingState;
	}

	public void setUsingState(String usingState) {
		this.usingState = usingState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateOperator() {
		return updateOperator;
	}

	public void setUpdateOperator(User updateOperator) {
		this.updateOperator = updateOperator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	public List<WeChatMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<WeChatMenu> subMenu) {
		this.subMenu = subMenu;
	}
	
	
	@Transient
	public String getDisplayName() {
		StringBuilder sb = new StringBuilder();
		WeChatMenu wxmenu = this;
		sb.append(wxmenu.getName());
		wxmenu = wxmenu.getParent();
		while (wxmenu != null) {
			sb.insert(0, " - ");
			sb.insert(0, wxmenu.getName());
			wxmenu = wxmenu.getParent();
		}
		return sb.toString();
	}

	
	
}
