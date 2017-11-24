<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
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
	$(function() {
		$("#validForm").validate();
		$("input[name='name']").focus();
		if ($('#radio_type_view').is(':checked')) {
			type_view();
		} else {
			type_click();
		}
		if ($('#level_1').is(':checked')) {
			parentid_1();
		}
		if ($('#level_2').is(':checked')) {
			parentid_2();
		}	
	});
	function confirmDelete() {
		return confirm("<s:message code='confirmDelete'/>");
	}
	function parentid_1() {
		var parentid = document.getElementsByName("parentId")[0];
		parentid.setAttribute("readonly", "readonly");
		parentid.value = "";
		parentid.setAttribute("class", "form-control");
		var name = document.getElementsByName("name")[0];
		name.setAttribute("maxlength", "16");
	}
	function parentid_2() {
		var parentid = document.getElementsByName("parentId")[0];
		parentid.removeAttribute("readonly");
		parentid.setAttribute("class", "form-control required");
		var name = document.getElementsByName("name")[0];
		name.setAttribute("maxlength", "40");
	}
	function type_view() {
		var key = document.getElementsByName("key")[0];
		var url = document.getElementsByName("url")[0];
		key.setAttribute("readonly", "readonly");
		key.value = "";
		key.setAttribute("class", "form-control");
		url.removeAttribute("readonly");
		url.setAttribute("class", "form-control required");
	}

	function type_click() {
		var key = document.getElementsByName("key")[0];
		var url = document.getElementsByName("url")[0];
		url.setAttribute("readonly", "readonly");
		url.value = "";
		url.setAttribute("class", "form-control");
		key.removeAttribute("readonly");
		key.setAttribute("class", "form-control required");
	}
</script>
</head>
<body class="skin-blue content-body">
	<jsp:include page="/WEB-INF/views/commons/show_message.jsp" />
	<div class="content-header">
		<h1>
			<s:message code="wxmenu.management" />
			-
			<s:message code="${oprt=='edit' ? 'edit' : 'create'}" />
		</h1>
	</div>
	<div class="content">
		<div class="box box-primary">
			<form class="form-horizontal" id="validForm" action="save.do" method="post">
				<tags:search_params />
				<f:hidden name="id" value="${bean.id}" />
				<f:hidden name="createDate" value="${bean.createDate}" />
				<f:hidden name="operatorId" value="${bean.operator.id}" />
				<f:hidden name="usingState" value="${bean.usingState == null ? '0' : bean.usingState}" />
				<f:hidden name="updateOperatorId" value="${bean.updateOperator.id}" />
				<f:hidden name="updateDate" value="${bean.updateDate}" />
				<f:hidden name="isDel" value="${bean.isDel == null ? '0' : bean.isDel}" />
				<f:hidden name="position" value="${position}" />
				<input type="hidden" id="redirect" name="redirect" value="edit" />
				<div class="box-header with-border">
					<div class="btn-toolbar">
						<div class="btn-group">
							<shiro:hasPermission name="plug:wxmenu:create">
								<button class="btn btn-default" type="button" onclick="location.href='form.do?${searchstring}&oprt=create';"
									<c:if test="${oprt=='create'}"> disabled="disabled"</c:if>>
									<s:message code="create" />
								</button>
							</shiro:hasPermission>
						</div>
						<div class="btn-group">
							<shiro:hasPermission name="plug:wxmenu:delete">
								<button class="btn btn-default" type="button"
									onclick="if(confirmDelete()){location.href='delete.do?ids=${bean.id}&${searchstring}';}"
									<c:if test="${oprt=='create'}"> disabled="disabled"</c:if>>
									<s:message code="delete" />
								</button>
							</shiro:hasPermission>
						</div>
						<div class="btn-group">
							<button class="btn btn-default" type="button"
								onclick="location.href='list.do?${searchstring}';">
								<s:message code="return" />
							</button>
						</div>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"><em
									class="required">*</em> <s:message code="wxmenu.name" /></label>
								<div class="col-sm-8">
									<f:text name="name" value="${oprt=='edit' ? (bean.name) : ''}"
										class="form-control required" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"><em
									class="required">*</em> <s:message code="wxmenu.type" /></label>
								<div class="col-sm-8">
									<label class="radio-inline"> <f:radio
											id="radio_type_view" name="type" value="view"
											checked="${bean.type}" default="view" onclick="type_view()" />
										<s:message code="wxmenu.type.0" /></label> <label
										class="radio-inline"> <f:radio id="radio_type_click"
											name="type" value="click" checked="${bean.type}"
											onclick="type_click()" /> <s:message code="wxmenu.type.1" />
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"><s:message
										code="wxmenu.key" /></label>
								<div class="col-sm-8">
									<f:text name="key" value="${oprt=='edit' ? (bean.key) : ''}"
										class="form-control" maxlength="128" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"><s:message
										code="wxmenu.url" /></label>
								<div class="col-sm-8">
									<f:text name="url" value="${oprt=='edit' ? (bean.url) : ''}"
										class="form-control" maxlength="1024" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"><em class="required">*</em> <s:message code="wxmenu.level" /></label>
								<div class="col-sm-8">
									<label class="radio-inline"><f:radio id="level_1"
											name="level" value="1" checked="${bean.level}" default="1"
											onclick="parentid_1()" /> <s:message code="wxmenu.level.1" /></label>
									<label class="radio-inline"><f:radio id="level_2"
											name="level" value="2" checked="${bean.level}"
											onclick="parentid_2()" /> <s:message code="wxmenu.level.2" /></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!-- 
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label"><s:message code="wxmenu.parent" /></label>
							<div class="col-sm-8">
								<f:text name="parentId"
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')"
									value="${oprt=='edit' ? (parent.id) : ''}"
									class="form-control" maxlength="128" />
							</div>
						</div>
					</div>
					 -->
					 <div class="col-sm-6">
						<div class="form-group">
			            <label for="parentId"  class="col-sm-4 control-label"><s:message code="wxmenu.parent"/></label>
			            <div class="col-sm-8">
				           <select class="form-control required" id="parentId" name="parentId">
						      <f:options items="${menuList}" itemValue="id" itemLabel="name" selected="${bean.parent.id}"/>
						    </select>
			           	</div>
			          </div>
			        </div>
				</div>
		</div>
		<div class="box-footer">
			<button class="btn btn-primary" type="submit">
				<s:message code="save" />
			</button>
			<button class="btn btn-default" type="submit"
				onclick="$('#redirect').val('list');">
				<s:message code="saveAndReturn" />
			</button>
		</div>
		</form>
	</div>
	</div>
</body>
</html>