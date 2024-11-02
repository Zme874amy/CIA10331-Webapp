<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*"%>

<% //��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
   EventVO eventVO = (EventVO) request.getAttribute("eventVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʸ�Ʒs�W - addEvent.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body bgcolor='white' class="mx-auto p-2" style="width: 800px;">

<table id="table-1">
	<tr><td>
		 <h3>���ʸ�Ʒs�W - addEvent.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="event.do" name="form1">
<table class="table">
	
	<tr>
		<td>���ʦW��<font color=red><b>*</b></font></td>
		<td><input class="form-control" type="TEXT" name="name" value="${eventVO.name}" size="45"/></td>
	</tr>
	<tr>
		<td>�Ӯa�s��:<font color=red><b>*</b></font></td>
		<td><input class="form-control" type="TEXT" name="businessId"   value="${eventVO.businessId}" size="45"/></td>
	</tr>
	<tr>
		<td>�_�l���<font color=red><b>*</b></font></td>
		<td><input class="form-control" name="startDate" type="date" value="${eventVO.startDate}"></td>
	</tr>
	<tr>
		<td>�������<font color=red><b>*</b></font></td>
		<td><input class="form-control" name="endDate" type="date" value="${eventVO.endDate}"></td>
	</tr>
	<jsp:useBean id="catalogSvc" scope="page" class="com.catalog.model.CatalogService" />
	<tr>
		<td>��������:<font color=red><b>*</b></font></td>
		<td><select class="form-control" size="1" name="catalogId">
			<c:forEach var="catalogVO" items="${catalogSvc.all}">
				<option value="${catalogVO.id}" ${(eventVO.catalogId==catalogVO.id)? 'selected':'' } >${catalogVO.name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>����</td>
		<td><input class="form-control" name="price" type="number" value="${eventVO.price}"></td>
	</tr>
	<tr>
		<td>�y�z</td>
		<td><input class="form-control" name="description" type="text" value="${eventVO.description}"></td>
	</tr>
	<tr>
		<td>���A<font color=red><b>*</b></font></td>
		<td><input class="form-control" name="status" type="number" value="${eventVO.status}"></td>
	</tr>
	<tr>
		<td>�H�ƤW��</td>
		<td><input class="form-control" name="maximum" type="number" value="${eventVO.maximum}"></td>
	</tr>
	<tr>
		<td>�H�ƤU��</td>
		<td><input class="form-control" name="minimum" type="number" value="${eventVO.minimum}"></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input class="btn btn-primary mb-3 form-control" type="submit" value="�e�X�s�W"></FORM>

</body>

</html>