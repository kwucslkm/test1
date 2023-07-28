<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>인사관리 조회화면</h2>
	<form action="findMember.jsp">
		<input type ="radio" value="id" name="searchType">
		사원번호로 조회: <input type="text" name="idValue"><br>
		<input type="radio" value="dept" name="searchType">
		소속부서:
		<select name="deptValue">
			<option value="인사부">인사부</option>
			<option value="기획부">기획부</option>
			<option value="홍보부">홍보부</option>
			<option value="영업부">영업부</option>
			<option value="경리부">경리부</option>
			
		</select><br>
		<input type="submit" value="조회">
		<input type="button" onclick ="index()" value="취소">
	</form>
</body>
<script>
	const index=()=> {
		location.href="index.jsp";
	}
</script>
</html>


