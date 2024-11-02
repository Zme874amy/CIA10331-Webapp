<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%

    EventService eventSvc = new EventService();
    List<EventVO> list = eventSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有活動資料 - listAllEvent.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body bgcolor='white'>

<table >
	<tr><td>
		 <h3>所有員工資料 - listAllEvent.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath() %>/back-end/event/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table class="table table-striped">
  <thead>
	<tr>
		<th scope="col">活動編號</th>
		<th scope="col">商家編號</th>
		<th scope="col">活動名稱</th>
		<th scope="col">開始日期</th>
		<th scope="col">結束日期</th>
		<th scope="col">延後日期</th>
		<th scope="col">活動類別</th>
		<th scope="col">票價</th>
		<th scope="col">描述</th>
		<th scope="col">人數上限</th>
		<th scope="col">人數下限</th>
		<th scope="col">已報名</th>
		<th scope="col">狀態</th>
		<th scope="col">修改</th>
		<th scope="col">刪除</th>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="id"  value="${eventVO.id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end//event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
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