<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"/>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSSmsList" checkbox="true" fitColumns="false" title="我的消息列表" actionUrl="tSSmsController.do?mydatagrid" idField="id" fit="true" queryMode="group" sortName="createDate" sortOrder="desc">
   <t:dgCol title="common.esId"  field="id"  hidden="true"  queryMode="single" />
   <t:dgCol title="common.isRead" field="isRead"  replace="已读_1,未读_0"/>
   <%-- <t:dgCol title="common.messageType"  field="esType"  query="false" queryMode="single" dictionary="msgType"/> --%>
   <t:dgCol title="common.messageHeader"  field="esTitle" query="true" queryMode="single" />
   <t:dgCol title="common.sender"  field="esSender"  queryMode="single"/>
   <t:dgCol title="common.content_2"  field="esContent"  queryMode="single" width="260"/>
  <%--  <t:dgCol title="common.dateCreated"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss" query="false" queryMode="group" /> --%>
   <t:dgCol title="common.sendtime"  field="esSendtime"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single" />
   <t:dgCol title="common.operation" field="opt" width="80"/>
      <t:dgFunOpt funname="doRead(id,isRead)" title="common.read" urlclass="ace_button" urlfont="fa-trash-o"/>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript" charset="utf-8">
  $('#tSSmsList').datagrid({   
	    rowStyler:function(index,row){   
	        if (row.isRead!=1){
	            return 'font-weight:bold !important;';   
	        }   
	    }
	});
  
  function doRead(id,isRead){
	  	var addurl = "tSSmsController.do?goSmsDetail&id="+id;
		createdetailwindow("通知详情", addurl, 750, 600);
  }
  
 </script>