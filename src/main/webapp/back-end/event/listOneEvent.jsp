<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.event.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO"); //EventServlet.java(Concroller), 存入req的eventVO物件
%>

<html>
<head>
<title>活動資料 - listOneEvent.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動資料 - listOneEvent.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table class="table">
	<tr>
		<th>活動編號</th>
		<th>商家編號</th>
		<th>活動名稱</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>延後日期</th>
		<th>活動類別</th>
		<th>票價</th>
		<th>描述</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>已報名</th>
	</tr>
	<jsp:useBean id="catalogSvc" scope="page" class="com.catalog.model.CatalogService" />
	<tr>
		<td>${eventVO.id}</td>
		<td>${eventVO.businessId}</td>
		<td>${eventVO.name}</td>
		<td>${eventVO.startDate}</td>
		<td>${eventVO.endDate}</td>
		<td>${eventVO.delayDate}</td>
		<td>${catalogSvc.getOneCatalog(eventVO.catalogId).name}</td>
		<td>${eventVO.price}</td>
		<td>${eventVO.description}</td>
		<td>${eventVO.maximum}</td>
		<td>${eventVO.minimum}</td>
		<td>${eventVO.enrolled}</td>

	</tr>
</table>

</body>
</html>