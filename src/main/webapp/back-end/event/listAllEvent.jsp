<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%

    EventService eventSvc = new EventService();
    List<EventVO> list = eventSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����ʸ�� - listAllEvent.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white'>

<table >
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEvent.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath() %>/back-end/event/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table class="table table-striped">
  <thead>
	<tr>
		<th scope="col">���ʽs��</th>
		<th scope="col">�Ӯa�s��</th>
		<th scope="col">���ʦW��</th>
		<th scope="col">�}�l���</th>
		<th scope="col">�������</th>
		<th scope="col">������</th>
		<th scope="col">�������O</th>
		<th scope="col">����</th>
		<th scope="col">�y�z</th>
		<th scope="col">�H�ƤW��</th>
		<th scope="col">�H�ƤU��</th>
		<th scope="col">�w���W</th>
		<th scope="col">���A</th>
		<th scope="col">�ק�</th>
		<th scope="col">�R��</th>
	</tr>
  </thead>
	<%@ include file="page1.file" %> 
	<jsp:useBean id="catalogSvc" scope="page" class="com.catalog.model.CatalogService" />
	<tbody>
	<c:forEach var="eventVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<th scope="row">${eventVO.id}</th>
			<td>${eventVO.businessId}</td>
			<td>${eventVO.name}</td>
			<td>${eventVO.startDate}</td>
			<td>${eventVO.endDate}</td>
			<td>${eventVO.delayDate}</td>
			<td>${catalogSvc.getOneCatalog(eventVO.catalogId).name}</td>
			<td>${eventVO.price}</td>
			<td>${eventVO.description}</td>
			<td>${eventVO.status}</td>
			<td>${eventVO.maximum}</td>
			<td>${eventVO.minimum}</td>
			<td>${eventVO.enrolled}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="id"  value="${eventVO.id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end//event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="id"  value="${eventVO.id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="page2.file" %>
</body>
</html>