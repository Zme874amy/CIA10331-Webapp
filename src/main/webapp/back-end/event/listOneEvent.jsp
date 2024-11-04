<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.event.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO"); //EventServlet.java(Concroller), �s�Jreq��eventVO����
%>

<html>
<head>
<title>���ʸ�� - listOneEvent.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���ʸ�� - listOneEvent.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table class="table">
	<tr>
		<th>���ʽs��</th>
		<th>�Ӯa�s��</th>
		<th>���ʦW��</th>
		<th>�}�l���</th>
		<th>�������</th>
		<th>������</th>
		<th>�������O</th>
		<th>����</th>
		<th>�y�z</th>
		<th>�H�ƤW��</th>
		<th>�H�ƤU��</th>
		<th>�w���W</th>
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