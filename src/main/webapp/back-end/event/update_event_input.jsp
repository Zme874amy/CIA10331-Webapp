<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*"%>

<% //見com.event.controller.EventServlet.java第200(?)行存入req的empVO物件 (此為從資料庫取出的eventVO, 也可以是輸入格式有錯誤時的eventVO物件)
   EventVO eventVO = (EventVO) request.getAttribute("eventVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動資料修改 - update_event_input.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white' class="mx-auto p-2" style="width: 800px;">

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="event.do" name="form1">
<table class="table">
	<tr>
		<td>活動編號<font color=red><b>*</b></font></td>
		<td>${eventVO.id}</td>
	</tr>
	<tr>
		<th>商家編號</th>
		<td>${eventVO.businessId}</td>
	</tr>
	<tr>
		<th>活動名稱<font color=red><b>*</b></font></th>
		<td><input class="form-control" type="TEXT" name="name" value="${eventVO.name}" size="45"/></td>
	</tr>
	<tr>
		<th>開始日期<font color=red><b>*</b></font></th>
		<td><input class="form-control" name="startDate" type="date" value="${eventVO.startDate}"></td> 
	</tr>
	<tr>
		<th>結束日期<font color=red><b>*</b></font></th>
		<td><input class="form-control" name="endDate" type="date" value="${eventVO.endDate}"></td> 
	</tr>
	<tr>
		<th>延期日期</th>
		<td><input class="form-control" name="delayDate" type="date" value="${eventVO.delayDate}"></td> 
	</tr>
	<jsp:useBean id="catalogSvc" scope="page" class="com.catalog.model.CatalogService" />
	<tr>
		<td>活動類型:<font color=red><b>*</b></font></td>
		<td><select class="form-control" size="1" name="catalogId">
			<c:forEach var="catalogVO" items="${catalogSvc.all}">
				<option value="${catalogVO.id}" ${(eventVO.catalogId==catalogVO.id)? 'selected':'' } >${catalogVO.name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>票價</td>
		<td><input class="form-control" name="price" type="number" value="${eventVO.price}"></td>
	</tr>
	<tr>
		<td>描述</td>
		<td><textarea class="form-control" name="description">${eventVO.description}</textarea></td>
	</tr>
	<tr>
		<td>狀態<font color=red><b>*</b></font></td>
		<td><input class="form-control" name="status" type="number" value="${eventVO.status}"></td>
	</tr>
	<tr>
		<td>人數上限</td>
		<td><input class="form-control" name="maximum" type="number" value="${eventVO.maximum}"></td>
	</tr>
	<tr>
		<td>人數下限</td>
		<td><input class="form-control" name="minimum" type="number" value="${eventVO.minimum}"></td>
	</tr>
	<tr>
		<td>已報名</td>
		<td><input class="form-control" name="enrolled" type="number" value="${eventVO.enrolled}"></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${eventVO.id}">
<input type="hidden" name="businessId" value="${eventVO.businessId}">
<input class="form-control btn btn-primary mb-3" type="submit" value="送出修改"></FORM>
</body>

</html>