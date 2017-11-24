package com.jspxcms.plug.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PLUG_WECHATUSER", uniqueConstraints = @UniqueConstraint(columnNames = "wechat_openid"))
public class Wechatuser {
	private Integer subcribe;
	private String openid;
	private String nickname;
	private Integer sex;
	private String city;
	private String country;
	private String province;
	private String language;
	private String headimgurl;
	private Date subcribe_time;
	private String unionid;
	private String remark;
	private String tagid_list;
	private Integer isblacklist;

	@Transient
	public void applyDefaultValue() {
		if (getNickname() == null) {
			setNickname("");
		}
		if (getSex() == null) {
			setSex(0);
		}
		if (getCity() == null) {
			setCity("");
		}
		if (getCountry() == null) {
			setCountry("");
		}
		if (getLanguage() == null) {
			setLanguage("");
		}
		if (getHeadimgurl(0) == null) {
			setHeadimgurl("");
		}
		if (getUnionid() == null) {
			setUnionid("");
		}
		if (getRemark() == null) {
			setRemark("");
		}
		if (getTagid_list() == null) {
			setTagid_list("");
		}
		if (getIsblacklist() == null) {
			setIsblacklist(0);
		}

	}

	@Column(name = "wechat_subcribe", unique = true, nullable = false)
	public Integer getSubcribe() {
		return subcribe;
	}

	public void setSubcribe(Integer subcribe) {
		this.subcribe = subcribe;
	}

	@Id
	@Column(name = "wechat_openid", unique = true, nullable = false)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "wechat_nickname", nullable = true)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "wechat_sex", nullable = true)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "wechat_city", nullable = true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "wechat_country", nullable = true)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "wechat_province", nullable = true)
	public String getProvince() {
		return province;

	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "wechat_language", nullable = true)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "wechat_headimgurl", nullable = true)
	public String getHeadimgurl(int size) {
		String str = this.headimgurl;
		int i = str.length();
		if(i>0) {
			str = str.replace(str.charAt(i - 1) + "", String.valueOf(size));
		}
		return str;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "wechat_subcribe_time", nullable = true)
	public Date getSubcribe_time() {
		return subcribe_time;
	}

	public void setSubcribe_time(Date subcribe_time) {
		this.subcribe_time = subcribe_time;
	}

	@Column(name = "wechat_unionid", nullable = true)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "wechat_remark", nullable = true)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "wechat_tagid_list", nullable = true)
	public String getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(String tagid_list) {
		this.tagid_list = tagid_list;
	}

	@Column(name = "wechat_isblacklist", nullable = true)
	public Integer getIsblacklist() {
		return isblacklist;
	}

	public void setIsblacklist(Integer isblacklist) {
		this.isblacklist = isblacklist;
	}

}
