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
			<form id="joinForm" action="${pageContext.request.contextPath}/user/join" method="post">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디 중복 확인</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		<!-- 푸터 없음 -->
		<!-- 메인 푸터  자리-->
		
	</div>

</body>

<script type="text/javascript">

var idCheck = null //중복 아닐때까지 반드시 체크하기

/* 아이디 중복 확인 */
$("#btnIdCheck").on("click", function(){
	console.log("아이디 중복 확인 버튼 클릭")
	
	var id = $('[name = "id"]').val()
	
	if(id == "" || id == null){
		alert("아이디를 입력해주세요")
		return false
		
	}else if(id.length < 4){
		alert("4자리 이상의 아이디를 입력해주세요.")
		return false
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/user/idCheck",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(id),
		dataType : "json",
		
		success : function(result){
			console.log(result)
			
			if(result == "success"){
				idCheck = id
				$("#tdMsg").css("color", "blue")
				$("#tdMsg").html("사용 가능한 아이디입니다.")
				
			
			}else{
				$("#tdMsg").css("color", "red")
				$("#tdMsg").html("사용 불가능한 아이디입니다.")
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		} 
	})
})


/* 회원가입 */
$("#joinForm").on("submit", function(){
	console.log("회원가입 버튼 클릭")
	
	var id = $("#txtId").val()
	
	if(id == "" || id == null){
		alert("아이디를 입력해주세요.")
		return false
		
	}
	
	if(idCheck != id){
		$("#tdMsg").css("color", "red")
		$("#tdMsg").html("아이디 중복 확인을 해주세요.")
		alert("아이디 중복 확인을 완료해주세요.")
		return false
	}
	
	
	var password = $("#txtPassword").val()
	
	if(password == "" || password == null){
		alert("비밀번호를 입력해주세요")
		return false
	}else if(password.length < 8){
		alert("8자리 이상의 비밀번호를 입력해주세요.")
		return false
	}
	
	
	var userName = $("#txtUserName").val()
	
	if(userName == "" || userName == null){
		alert("이름을 입력해주세요")
		return false
	}
	
	
	var agree = $("#chkAgree").is(":checked")
	
	if(agree == false){
		alert("약관에 동의하지 않으면 서비스를 이용하실 수 없습니다.")
		return false
	}
	
	return true
	
})


</script>

</html>