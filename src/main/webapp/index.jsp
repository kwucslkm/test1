
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>인사관리 시스템</h2>
	<button onclick="find()"> 조회 </button>
	<button onclick="save()"> 사원등록 </button>
	<button onclick="update()"> 정보변경 </button>
	<button onclick="deleteMember()"> 퇴사 </button>
	<button onclick="window.close()"> 종료 </button>
</body>
<script>
	const find = () => {
		location.href ="findMemberForm.jsp";
	}
	
	const save = () => {
		location.href ="saveMemberForm.jsp";
	}
	
	const update = () => {
		location.href ="updateMemberForm.jsp";
	}
	
	const deleteMember = () => {
		location.href ="deleteMemberForm.jsp";
	}
	
	
</script>
</html>