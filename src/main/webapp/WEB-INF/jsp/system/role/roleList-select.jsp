<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>common.role.list</title>
<t:base type="jquery,easyui,tools"/>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="roleList" title="common.role.select" actionUrl="userController.do?datagridRole" idField="id"  showRefresh="false" >
	<t:dgCol title="common.code" field="id" hidden="true"/>
	<t:dgCol title="common.role.name" field="roleName" width="50"/>
</t:datagrid>
</body>
</html>
<script type="text/javascript">
function getRole(){
		var rowsData = $("#roleList").datagrid("getChecked");
		var id = "";
		if(rowsData.length==0){
			tip('<t:mutiLang langKey="please.select.role"/>');
			return "";
		}
		return rowsData[0].id;
	}
</script>