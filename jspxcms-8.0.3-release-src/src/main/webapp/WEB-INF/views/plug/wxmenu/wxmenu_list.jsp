<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fnx" uri="http://java.sun.com/jsp/jstl/functionsx"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.jspxcms.com/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/head.jsp"/>
<script>
$(function() {
	$("#sortHead").headSort();
});

function optSingle(opt) {
	if(Cms.checkeds("ids")==0) {
		alert("<s:message code='pleaseSelectRecord'/>");
		return false;
	}
	if(Cms.checkeds("ids")>1) {
		alert("<s:message code='pleaseSelectOne'/>");
		return false;
	}
	var id = $("input[name='ids']:checkbox:checked").val();
	location.href=$(opt+id).attr("href");
}
function optMulti(form, action, msg) {
	if(Cms.checkeds("ids")==0) {
		alert("<s:message code='pleaseSelectRecord'/>");
		return false;
	}
	if(msg && !confirm(msg)) {
		return false;
	}
	form.action=action;
	form.submit();
	return true;
}
function optDelete(form) {
	if(Cms.checkeds("ids")==0) {
		alert("<s:message code='pleaseSelectRecord'/>");
		return false;
	}
	if(!confirmDelete()) {
		return false;
	}
	form.action='delete.do';
	form.submit();
	return true;
}

function confirmDelete() {
	return confirm("<s:message code='confirmDelete'/>");
}

function confirmCancleEnable() {
	return confirm("<s:message code='confirmCancleEnable'/>");
}

function confirmEnable() {	
	return confirm("<s:message code='confirmEnable'/>");
}
</script>
</head>
<body class="skin-blue content-body">
<jsp:include page="/WEB-INF/views/commons/show_message.jsp"/>
<div class="content-header">
	<h1><s:message code="wxmenu.management"/> - <s:message code="list"/> <small>(<s:message code="totalElements" arguments="${pagedList.totalElements}"/>)</small></h1>
</div>
<div class="content">
	<div class="box box-primary">
		<div class="box-body table-responsive">
			<form action="list.do" method="get" class="form-inline ls-search">
				<div class="form-group">
				  <label for="search_CONTAIN_parent.name"><s:message code="wxmenu.parent"/></label>
				  <input class="form-control input-sm" type="text" id="search_CONTAIN_parent.name" name="search_CONTAIN_parent.name" value="${requestScope['search_CONTAIN_parent.name'][0]}" style="width:100px;"/>
				</div>
				<div class="form-group">
				  <label for="search_CONTAIN_name"><s:message code="wxmenu.name"/></label>
				  <input class="form-control input-sm" type="text" id="search_CONTAIN_name" name="search_CONTAIN_name" value="${requestScope['search_CONTAIN_name'][0]}" style="width:100px;"/>
				</div>
				<div class="form-group">
				  <label for="search_EQ_type"><s:message code="wxmenu.type"/></label>
			      <select class="form-control input-sm" id="search_EQ_type" name="search_EQ_type">
			        <option value=""><s:message code="allSelect"/></option>
			        <option value="view" <c:if test="${'view' eq search_EQ_type[0]}"> selected="selected"</c:if>><s:message code="wxmenu.type.0"/></option>
			        <option value="click" <c:if test="${'click' eq search_EQ_type[0]}"> selected="selected"</c:if>><s:message code="wxmenu.type.1"/></option>			        
			      </select>
				</div>
				<div class="form-group">
				  <label for="search_EQ_level"><s:message code="wxmenu.level"/></label>
			      <select class="form-control input-sm" id="search_EQ_level" name="search_EQ_level">
			        <option value=""><s:message code="allSelect"/></option>
			        <option value="1"<c:if test="${'1' eq search_EQ_level[0]}"> selected="selected"</c:if>><s:message code="wxmenu.level.1"/></option>
			        <option value="2"<c:if test="${'2' eq search_EQ_level[0]}"> selected="selected"</c:if>><s:message code="wxmenu.level.2"/></option>			        
			      </select>
				</div>
				<div class="form-group">
				  <label for="search_CONTAIN_operator.username"><s:message code="wxmenu.operator"/></label>
				  <input class="form-control input-sm" type="text" id="search_CONTAIN_operator.username" name="search_CONTAIN_operator.username" value="${requestScope['search_CONTAIN_operator.username'][0]}" style="width:100px;"/>
				</div>
				<div class="form-group">
				  <label for="search_EQ_usingState"><s:message code="wxmenu.usingState"/></label>
			      <select class="form-control input-sm" id="search_EQ_usingState" name="search_EQ_usingState">
			        <option value=""><s:message code="allSelect"/></option>
			        <option value="0"<c:if test="${'0' eq search_EQ_usingState[0]}"> selected="selected"</c:if>><s:message code="wxmenu.usingState.0"/></option>
			        <option value="1"<c:if test="${'1' eq search_EQ_usingState[0]}"> selected="selected"</c:if>><s:message code="wxmenu.usingState.1"/></option>			        
			      </select>
				</div>
				<div class="form-group">
				  <label><s:message code="wxmenu.updateDate"/></label>
			      <f:text class="form-control input-sm" name="search_GTE_updateDate_Date" value="${search_GTE_updateDate_Date[0]}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width:85px;"/>
			      <span>至</span>
			      <f:text class="form-control input-sm" name="search_LTE_updateDate_Date" value="${search_LTE_updateDate_Date[0]}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width:85px;"/>
				</div>		
			  <button class="btn btn-default btn-sm" type="submit"><s:message code="search"/></button>
			</form>
			<form method="post">
				<tags:search_params/>
				<div class="btn-toolbar ls-btn-bar">
					<div class="btn-group">
						<shiro:hasPermission name="plug:wxmenu:create">
						<button class="btn btn-default" type="button" onclick="location.href='form.do?${searchstring}&oprt=create';"><s:message code="create"/></button>
						</shiro:hasPermission>
					</div>
					<div class="btn-group">
						<shiro:hasPermission name="plug:wxmenu:enable">
						<button class="btn btn-default" type="button" onclick="return optMulti(this.form,'enable.do');"><s:message code="enable"/></button>
						</shiro:hasPermission>
						<shiro:hasPermission name="plug:wxmenu:cancleEnable">
						<button class="btn btn-default" type="button" onclick="return optMulti(this.form,'cancleEnable.do');"><s:message code="cancleEnable"/></button>
						</shiro:hasPermission>
						<shiro:hasPermission name="plug:wxmenu:edit">
						<button class="btn btn-default" type="button" onclick="return optSingle('#edit_opt_');"><s:message code="edit"/></button>
						</shiro:hasPermission>
					</div>
					<div class="btn-group">
						<shiro:hasPermission name="plug:wxmenu:delete">	
						<button class="btn btn-default" type="button" onclick="return optDelete(this.form);"><s:message code="delete"/></button>
						</shiro:hasPermission>
					</div>
					<div class="btn-group">
						<shiro:hasPermission name="plug:wxmenu:SynchronousUpdate">
						<button class="btn btn-default" type="button" onclick="location.href='createMenu.do?${searchstring}&oprt=create';"><s:message code="SynchronousUpdate"/></button>
						</shiro:hasPermission>
					</div>
				</div>
				<table id="pagedTable" class="table table-condensed table-bordered table-hover ls-tb">
				  <thead id="sortHead" pagesort="<c:out value='${page_sort[0]}' />" pagedir="${page_sort_dir[0]}" pageurl="list.do?page_sort={0}&page_sort_dir={1}&${searchstringnosort}">
				  <tr class="ls_table_th">
				    <th width="25"><input type="checkbox" onclick="Cms.check('ids',this.checked);"/></th>
				    <th width="150"><s:message code="operate"/></th>
				    <th width="30" class="ls-th-sort"><span class="ls-sort" pagesort="id">ID</span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="name"><s:message code="wxmenu.name"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="type"><s:message code="wxmenu.type"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="level"><s:message code="wxmenu.level"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="parent.id"><s:message code="wxmenu.parent"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="key"><s:message code="wxmenu.key"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="url"><s:message code="wxmenu.url"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="operator.id"><s:message code="wxmenu.operator"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="usingState"><s:message code="wxmenu.usingState"/></span></th>
				    <th class="ls-th-sort"><span class="ls-sort" pagesort="updateDate"><s:message code="wxmenu.updateDate"/></span></th>				    
				  </tr>
				  </thead>
				  <tbody>	  
				  <c:forEach var="bean" varStatus="status" items="${pagedList.content}">
				  <tr<shiro:hasPermission name="plug:wxmenu:edit"> ondblclick="location.href=$('#edit_opt_${bean.id}').attr('href');"</shiro:hasPermission>>
				    <td><input type="checkbox" name="ids" value="${bean.id}"/></td>
				    <td align="center" width="200px">					      		    	
					  <shiro:hasPermission name="plug:wxmenu:enable">
				      <a href="enable.do?ids=${bean.id}&${searchstring}" onclick="return confirmEnable();" class="ls-opt" ><s:message code="enable"/></a>
				      </shiro:hasPermission>
				       <shiro:hasPermission name="plug:wxmenu:cancleEnable">
				      <a href="cancleEnable.do?ids=${bean.id}&${searchstring}" onclick="return confirmCancleEnable();" class="ls-opt" ><s:message code="cancleEnable"/></a>
				      </shiro:hasPermission>
					  <shiro:hasPermission name="plug:wxmenu:edit">
				      <a id="edit_opt_${bean.id}" href="form.do?id=${bean.id}&oprt=edit&position=${pagedList.number*pagedList.size+status.index}&${searchstring}" class="ls-opt"><s:message code="edit"/></a>
				      </shiro:hasPermission>					 
					  <shiro:hasPermission name="plug:wxmenu:delete">
						<a href="delete.do?ids=${bean.id}&${searchstring}" onclick="return confirmDelete();" class="ls-opt"><s:message code="delete"/></a> 
				      </shiro:hasPermission>
				     </td>
				     <td>${bean.id}</td>
				     <td>${bean.name}</td>
				     <td>${bean.type}</td>
				     <td name="level"><s:message code="wxmenu.level.${bean.level}"/></td>
				     <td>${bean.parent.name}</td>
				     <td>${bean.key}</td>
				     <td>${bean.url}</td>
				     <td>${bean.operator.username}</td>
				     <td name="usingState"><s:message code="wxmenu.usingState.${bean.usingState}"/></td>
				     <td>${bean.updateDate}</td>
				  </tr>
				  </c:forEach>
				  </tbody>
				</table>
				<c:if test="${fn:length(pagedList.content) le 0}"> 
				<div class="ls-norecord"><s:message code="recordNotFound"/></div>
				</c:if>
				</form>
				<form action="list.do" method="get" class="ls-page">
					<tags:search_params excludePage="true"/>
				  <tags:pagination pagedList="${pagedList}"/>
				</form>
			</form>
		</div>
	</div>
</div>


</body>
</html>