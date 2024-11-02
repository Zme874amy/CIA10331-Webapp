<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*"%>

<% //��com.event.controller.EventServlet.java��200(?)��s�Jreq��empVO���� (�����q��Ʈw���X��eventVO, �]�i�H�O��J�榡�����~�ɪ�eventVO����)
   EventVO eventVO = (EventVO) request.getAttribute("eventVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʸ�ƭק� - update_event_input.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white' class="mx-auto p-2" style="width: 800px;">

<table id="table-1">
	<tr><td>
		 <h3>���u��ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

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
		<td>���ʽs��<font color=red><b>*</b></font></td>
		<td>${eventVO.id}</td>
	</tr>
	<tr>
		<th>�Ӯa�s��</th>
		<td>${eventVO.businessId}</td>
	</tr>
	<tr>
		<th>���ʦW��<font color=red><b>*</b></font></th>
		<td><input class="form-control" type="TEXT" name="name" value="${eventVO.name}" size="45"/></td>
	</tr>
	<tr>
		<th>�}�l���<font color=red><b>*</b></font></th>
		<td><input class="form-control" name="startDate" type="date" value="${eventVO.startDate}"></td> 
	</tr>
	<tr>
		<th>�������<font color=red><b>*</b></font></th>
		<td><input class="form-control" name="endDate" type="date" value="${eventVO.endDate}"></td> 
	</tr>
	<tr>
		<th>�������</th>
		<td><input class="form-control" name="delayDate" type="date" value="${eventVO.delayDate}"></td> 
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
		<td><textarea class="form-control" name="description">${eventVO.description}</textarea></td>
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
	<tr>
		<td>�w���W</td>
		<td><input class="form-control" name="enrolled" type="number" value="${eventVO.enrolled}"></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${eventVO.id}">
<input type="hidden" name="businessId" value="${eventVO.businessId}">
<input class="form-control btn btn-primary mb-3" type="submit" value="�e�X�ק�"></FORM>
</body>

</html>