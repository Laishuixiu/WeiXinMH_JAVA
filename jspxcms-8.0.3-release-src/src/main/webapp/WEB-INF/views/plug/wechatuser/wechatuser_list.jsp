<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<jsp:include page="/WEB-INF/views/head.jsp" />
<script type="text/javascript"> 
	function upload(){
		var x=document.getElementById("tagname").value;
		location.href="createtag.do?tagname="+x;
	}
	
	function taglist(){
		var x=document.getElementById("tagid").value;
		location.href="taglist.do?tagid="+x;
	}
	$(function() {

		$("#sortHead").headSort();
	});
	function confirmCancleUpload() {
		return confirm("<s:message code='confirmCancleUpload'/>");
	}
	function confirmUpload() {
		return confirm("<s:message code='confirmUpload'/>");
	}
	function optSingle(opt) {
		if (Cms.checkeds("ids") == 0) {
			alert("<s:message code='pleaseSelectRecord'/>");
			return false;
		}
		if (Cms.checkeds("ids") > 1) {
			alert("<s:message code='pleaseSelectOne'/>");
			return false;
		}
		var id = $("input[name='ids']:checkbox:checked").val();
		location.href = $(opt + id).attr("href");
	}
	function optDelete(form) {
		if (Cms.checkeds("ids") == 0) {
			alert("<s:message code='pleaseSelectRecord'/>");
			return false;
		}
		if (!confirmDelete()) {
			return false;
		}
		form.action = 'delete.do';
		form.submit();
		return true;
	}
	function optUpload(form) {
		if (Cms.checkeds("ids") == 0) {
			alert("<s:message code='pleaseSelectRecord'/>");
			return false;
		}
		if (!confirmUpload()) {
			return false;
		}
		form.action = 'upload.do';
		form.submit();
		return true;
	}
	function optReload(form) {
		form.action = 'reload.do';
		form.submit();
		return true;
	}
	function optCancleUpload(form) {
		if (Cms.checkeds("ids") == 0) {
			alert("<s:message code='pleaseSelectRecord'/>");
			return false;
		}
		if (!confirmCancleUpload()) {
			return false;
		}
		form.action = 'cancleupload.do';
		form.submit();
		return true;
	}
</script>

<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body class="skin-blue content-body">
	<jsp:include page="/WEB-INF/views/commons/show_message.jsp" />
	<div class="content-header">
		<h1>
			<s:message code="wxmenu.management" />
			-
			<s:message code="list" />
		</h1>
	</div>
	<div class="content">
		<div class="box box-primary">
			<div class="box-body table-responsive">
				<div class="btn-toolbar ls-btn-bar">
					<shiro:hasPermission name="plug:wechatuser:list">
						<button class="btn btn-default" type="button" onclick="location.href='list.do';"><s:message code="wechat.issubcribe"/></button>
					</shiro:hasPermission>
					<shiro:hasPermission name="plug:wechatuser:blacklist">
						<button class="btn btn-default" type="button" onclick="location.href='blacklist.do';"><s:message code="wechat.isblacklist"/></button>
					</shiro:hasPermission>
				</div>
				<div>
				<c:if test="${oprt=='list'}">
				<form class="form-inline ls-search" action="list.do" method="get">
					<div class="form-group" style="float: left">
						<input class="form-control input-sm" size="40" type="text" name="search_CONTAIN_nickname" value="${search_CONTAIN_nickname[0]}"
							placeholder="<s:message code="wechat.nickname" />" />
					
					<button class="btn btn-default btn-sm" type="submit" style="margin-bottom: 5px">
						<s:message code="search" />
					</button>
					</div>
					<div class="form-group" style="float: right">
						<%-- <shiro:hasPermission name="plug:wechatuser:addtag">
								<button class="btn btn-default" type="button" onclick="location.href='createtag.do'">
									<s:message code="wechat.createtag" />
								</button>
						</shiro:hasPermission> --%>
						<button class="btn btn-primary" data-toggle="modal" type="button" data-target="#myModal">
							新建标签
						</button>
						<!-- 模态框（Modal） -->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
											&times;
										</button>
										<h4 class="modal-title" id="myModalLabel">
											输入标签名称：
										</h4>
									</div>
									<div class="modal-body">
											<input type="text" id="tagname" placeholder="在这里输入标签名称">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">
											关闭
										</button>
										<button type="button" class="btn btn-primary" onclick="upload()">
											新建
										</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
						
					</div>
				</form>
				</c:if>
				</div>
				<c:choose>
				<c:when test="${oprt=='list'}">
				<div style="width: 80%;float: left">
				</c:when>
				<c:otherwise>
				<div style="width:100%;float: left">
				</c:otherwise>
				</c:choose>
				<div>
					<c:if test="${oprt=='blacklist'}">
						<div style="font-size: 20px"><c:out value="黑名单"></c:out></div>
					</c:if>
					<c:if test="${oprt=='list'}">
							<c:choose>
								<c:when test="${tagname==null}">
									<div style="font-size: 20px"><s:message code="wechat.alluser"></s:message></div>
								</c:when>
								<c:otherwise>
									<button style="font-size: 20px">123123123</button>
									<shiro:hasPermission name="plug:wechat:renametag">
										<button class="btn btn-default" type="button" onclick="location.href='renametag.do?name=${tagname}'">
											<s:message code="wechat.renametag" />
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="plug:wechat:deletetag">
										<button class="btn btn-default" type="button" onclick="location.href='deletetag.do?name=${tagname}'" >
											<s:message code="wechat.deletetag" />
										</button>
									</shiro:hasPermission>
								</c:otherwise>
							</c:choose>
					</c:if>
				</div>
				
				<form method="post">
					<tags:search_params />
						<table id="pagedTable" class="table table-condensed table-bordered table-hover ls-tb">
							<thead id="sortHead" pagesort="<c:out value='${page_sort[0]}' />"
								pagedir="${page_sort_dir[0]}"
								pageurl="list.do?page_sort={0}&page_sort_dir={1}&${searchstringnosort}">
								<tr class="ls_table_th">
									<th width="25"><input type="checkbox" onclick="Cms.check('ids',this.checked);" /></th>
									<c:choose>
									<c:when test="${oprt=='list'}">
									<td>
									<shiro:hasPermission name="plug:wechat:addtag">
									<button class="btn btn-default" type="button" onclick="return optCancleUpload(this.form);" style="float: left;">
										<s:message code="wechat.addtag" />
									</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="plug:wechat:addblacklist">
									<button class="btn btn-default" type="button" onclick="return optCancleUpload(this.form); " style="float: left;">
										<s:message code="wechat.addblacklist" />
									</button>
									</shiro:hasPermission>
									</td>
									</c:when>
									<c:otherwise>
									<td>
									<shiro:hasPermission name="plug:wechat:outblacklist">
									<button class="btn btn-default" type="button" onclick="return optCancleUpload(this.form);" style="float: left;">
										<s:message code="wechat.outblacklist" />
									</button>
									</shiro:hasPermission>
									</td>
									</c:otherwise>
									</c:choose>
								
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="bean" varStatus="status" items="${pagedList.content}">
									<tr>
									<td><input type="checkbox" name="ids" value="${bean.openid}" /></td>
									<td><img src="${bean.headimgurl}"></td>
									<td>
									<c:choose>
									<c:when test="${empty bean.remark}"><c:out value="${bean.nickname}" /></c:when>
									<c:otherwise><c:out value="${bean.remark}"/>(<c:out value="${bean.nickname}"/>)</c:otherwise>
									</c:choose>
									</td>
									<td>
										<c:forEach var="tag" varStatus="status" items="${taglist.content}">
											<a href="#?tagid=${tag.id}"><c:out value="tag.name"/></a>									
										</c:forEach>
									</td>
									<td>
										<shiro:hasPermission name="plug:wechat:addtag">
											<button style="text-align: right" class="btn btn-default" type="button" onclick="">
										<s:message code="wechat.updateremark" />
									</button>
									</shiro:hasPermission>
									</td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
						<c:if test="${fn:length(pagedList.content) le 0}">
							<div class="ls-norecord">
								<s:message code="recordNotFound" />
							</div>
						</c:if>
				</form>
				<form action="list.do" method="get" class="ls-page">
					<tags:search_params excludePage="true" />
					<tags:pagination pagedList="${pagedList}" />
				</form>
				</div>
				<c:if test="${oprt=='list'}">
				<div style="float:left;width: 20%">
					<a href="#" style="font-size:20px"><c:out value="全部用户(${pageList.totalElements})"/></a>
					<tbody>
					<c:forEach var="tag" varStatus="status" items="${tagList.content}">
						<tr>
							<a style="font-size: 15px;display: block;padding-left: 25px;" onclick="taglist()"><c:out value="${tag.name}(${tag.count})"/></a>
							<input type="hidden" id="tagid" value="${tag.id}"/>
						</tr>
					</c:forEach>
					</tbody>
				</div>
				</c:if>
				
			</div>
		</div>
	</div>
</body>
</html>