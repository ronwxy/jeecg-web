<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/context/mytags.jsp"%>
<t:datagrid name="operationList" title="接口权限规则" actionUrl="interfaceController.do?ruledatagrid&interfaceId=${interfaceId}" idField="id">
	<t:dgCol title="common.id" field="id" hidden="true"/>
	<t:dgCol title=" 规则名称" field="ruleName" width="50"/>
    <t:dgCol title=" 规则字段" field="ruleColumn" width="50"/>
    <t:dgCol title=" 规则条件" field="ruleConditions" width="50"/>
    <t:dgCol title=" 规则值" field="ruleValue" width="50" hidden="true"/>
    <t:dgCol title="common.operation" field="opt" width="85"/>
	<t:dgDelOpt url="interfaceController.do?delrule&id={id}" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgDelOpt>
    <t:dgFunOpt funname="editoperation(id,operationname)" title="common.edit" urlclass="ace_button" urlfont="fa-edit"/>
	<t:dgToolBar title="common.add.param" langArg="common.operation" icon="icon-add" url="interfaceController.do?addorupdaterule&interfaceId=${interfaceId}" funname="add"/>
	</t:datagrid>
<script type="text/javascript">

function editoperation(operationId,operationname)
{
	createwindow("<t:mutiLang langKey="common.edit.param" langArg="common.operation"/>","interfaceController.do?addorupdaterule&interfaceId=${interfaceId}&id="+operationId);
}
</script>
