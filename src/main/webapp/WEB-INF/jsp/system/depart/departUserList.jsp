<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/context/mytags.jsp"%>
<t:datagrid name="departUserList" title="common.operation"
            actionUrl="departController.do?userDatagrid&departid=${departid}" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="true"/>
	<t:dgCol title="common.username" sortable="false" width="100" field="userName" query="true"/>
	<t:dgCol title="common.real.name" field="realName" width="100" query="true"/>
	<t:dgCol title="common.status" sortable="true" width="50" field="status" replace="common.active_1,common.inactive_0,super.admin_-1"/>
	<t:dgCol title="common.operation" field="opt" width="100"/>
	<t:dgDelOpt title="common.delete" url="departController.do?delUserOrg&userid={id}&departid=${departid }" urlclass="ace_button"  urlfont="fa-trash-o"/>
	<t:dgToolBar title="common.add.param" langArg="common.user" icon="icon-add" url="userController.do?addorupdate&departid=${departid}" funname="add"/>
	<t:dgToolBar title="common.edit.param" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate&departid=${departid}" funname="update"/>
	<t:dgToolBar title="common.add.exist.user" icon="icon-add" url="departController.do?goAddUserToOrg&orgId=${departid}" funname="add" width="600"/>
</t:datagrid>
