<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="center-content">
		
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!--메인 해더 자리 -->
		
		
		<div>
			<form id="loginForm" method="post" action="${pageContext.request.contextPath}/user/login">
	      		<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="textId">아이디</label></td>
		      			<td><input id="textId" type="text" name="id"></td>
		      		</tr>
		      		<tr>
		      			<td><label for="textPassword">패스워드</label> </td>
		      			<td><input id="textPassword" type="password" name="password"></td>   
		      			   			
		      		</tr> 
		      		<tr>
		      			<td colspan="2" id="tdMsg" colspan="2">
		      				<span style="color: #FF0000;">
		      					<c:if test = "${param.result == 'fail'}">아이디와 비밀번호를 확인해주세요.</c:if>
		      				</span>
		      			</td>
		      		</tr> 
		      	</table>
	      		<div id="btnArea">
					<button class="btn" type="submit" >로그인</button>
				</div>
	      		
			</form>
		
		</div>
		
		<!-- 푸터 없음 -->
		<!-- 메인 푸터  자리-->
		
		
	</div>
	
</body>

<script type="text/javascript">

/* 로그인 */
$("#loginForm").on("submit", function(){
	console.log("로그인 버튼 클릭")
	
	var id = $("#textId").val()
	var password = $("#textPassword").val()
	
	if(id == "" || id == null || password == "" || password == null){
		$("#tdMsg").css("color", "red")
		$("#tdMsg").html("아이디와 비밀번호를 모두 입력해주세요.")
		return false
	}
	
	return true
	
})

</script>

</html>