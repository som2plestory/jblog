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
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div>
				<form id="admin-content" action="${pageContext.request.contextPath}/${authUser.id}/admin/post/insert" 
					  method="post">
			      	<table id="admin-write">
			      		<colgroup>
							<col style="width: 100px;">
							<col style="">
							<col style="">
						</colgroup>
			      		<tr>
			      			<td class="t">포스트 제목</td>
			      			<td >
			      				<input type="text" name="postTitle">
				      		</td>
				      		<td>
				      			<select name="cateNo">
				      				<!-- 카테고리 리스트 영역 -->
				      				<c:forEach items="${cateIndex}" var="cateVo">
					      				<option value="${cateVo.cateNo}"
					      					<c:if test="${cateVo.cateName == '미분류'}">
					      						selected="selected"
					      					</c:if>	
					      					>${cateVo.cateName}
					      				</option>
				      				</c:forEach>
				      				<!-- 카테고리 리스트 영역 -->
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td>내용</td>
			      			<td colspan="2"><textarea name="postContent"></textarea></td>
			      		</tr>
			      	</table>
			      	<div id="btnArea">
			      		<button class="btn_l" type="submit" >포스트하기</button>
			      	</div>
				</form>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

$("#admin-content").on("submit", function(){
	console.log("포스트 버튼 클릭")
	
	var postTitle = $("[name='postTitle']").val()
	
	if(postTitle == "" || postTitle == null){
		alert("제목을 입력해주세요.")
		
		return false
	}
	
	return true
})


</script>

</html>
