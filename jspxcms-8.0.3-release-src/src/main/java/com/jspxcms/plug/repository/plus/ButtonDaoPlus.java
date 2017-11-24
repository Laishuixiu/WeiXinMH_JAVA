package com.jspxcms.plug.repository.plus;

import java.util.List;

import com.jspxcms.common.orm.Limitable;
import com.jspxcms.plug.domain.Button;

public interface ButtonDaoPlus {

	public List<Button> getList(Integer[] siteId, Limitable limitable);
}
