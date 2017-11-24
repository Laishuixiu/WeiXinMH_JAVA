package com.jspxcms.plug.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PLUG_WECHATTAG", uniqueConstraints = @UniqueConstraint(columnNames = "wechat_id"))
public class Wechattag {
	
	@Transient
	public void applyDefaultValue() {
		if (getCount() == null) {
			setCount(0);
		}
	}
	
	
	private Integer id;
	private String name;
	private Integer count;
	
	@Id
	@Column(name = "wechat_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "wechat_name",nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "wechat_count",nullable = true)
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
