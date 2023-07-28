<%@page import="com.test.test1.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	System.out.println("save호출");
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String dept = request.getParameter("dept");
	String position = request.getParameter("position");
	String duty = request.getParameter("duty");
	String phone = request.getParameter("phone");
	
	MemberDAO memberDAO = new MemberDAO();
	int result = memberDAO.saveMember(id, name, dept, position, duty, phone);
	if (result > 0) {
	%>
	<script>
		alert("저장 성공! index.jsp로 돌아갑니다.")
		location.href = "index.jsp";
	</script>
	<%
	} else {
	%>

	<script>
		alert("저장 실패");
	</script>
	<%
	}
	%>
	<!-- 스크립틀릿에서 선언한 자바변수값 출력 -->
	<h3>전달받은 값</h3>
	<p><%=id%></p>
	<p><%=name%></p>
	<p><%=dept%></p>
	<p><%=position%></p>
	<p><%=duty%></p>
	<p><%=phone%></p>

</body>
</html>