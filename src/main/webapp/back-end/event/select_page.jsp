<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>G4 Event: Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body bgcolor='white' class="mx-auto p-2" style="width: 800px;">

<table class="table">
   <tr><td><h3>G4 Event: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Event G4: Home</p>

	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="row align-items-top gx-5">
<table class="table col">
	<tr><th>��Ƭd��:</th></tr>
	<tr><td>
	  <a href='listAllEvent.jsp'>List</a> all Events.  <br><br>
	</td></tr>
	<tr><td>
	    <FORM class="row g-3" METHOD="post" ACTION="event.do" >
		    <div class="col-auto">
		    	<label class="form-label">���ʬd��</label>
		    </div>
		    <div class="col-auto">
		        <input class="form-control col-auto" type="text" placeholder="��J���ʽs��(�Ҧp 1)" name="id" class="form-control">
		        <input type="hidden" name="action" value="getOne_For_Display">
		    </div>
		    <div class="col-auto">
		        <input class="btn btn-primary mb-3 col-auto" type="submit" value="�e�X">
		    </div>
	    </FORM>
	</td></tr>

</table>
  


<%--   <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܭ��u�s��:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.empno} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܭ��u�m�W:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>

<table class="table col">
	<thead>
		<tr><th>���ʺ޲z</th></tr>
	</thead>
	<tbody>
		<tr><td>
  		<a href='addEvent.jsp'>Add</a> a new Event.
		</td></tr>
	</tbody>
</table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>