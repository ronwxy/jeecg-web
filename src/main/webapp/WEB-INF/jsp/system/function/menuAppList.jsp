<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
	<head>
<t:base type="jquery-webos,easyui,tools,DatePicker,autocomplete"/>
		<link href="plug-in/sliding/css/main.css" rel="stylesheet" type="text/css" />

        <script>

            $(function(){
            	$(".menuSearch_Info").live("click",function(){
            		//$(this).blur();
            			var url=$(this).attr("url");
            			var icon=$(this).attr("icon");
            			var id=$(this).attr("id");
            			var title=$(this).attr("title");
            			//window.parent.close();
            			createwindow(title,url,1000,500);
            		})
            });

        </script>
	</head>
	<body>
	${menuListMap }
	</body>
</html>