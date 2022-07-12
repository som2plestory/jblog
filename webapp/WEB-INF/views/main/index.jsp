<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css" type="text/css">

</head>
<body>
	<div id="center-content">
		
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!--메인 해더 자리 -->
		
		
		<form id="search-form" action="${pageContext.request.contextPath}/search" method="get">
			<fieldset>
				<input type="text" name="keyword" value="${sMap.keyword}">
				<button id="btnSearch" type="submit" >검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">포스트 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="postTitle"
					<c:if test="${sMap.kwdOpt == 'postTitle'}">
						checked="checked"
					</c:if>
				> 
				
				<label for="rdo-userName">포스트 내용</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="postContent" 
					<c:if test="${sMap.kwdOpt == 'postContent'}">
						checked="checked"
					</c:if>
				> 
			</fieldset>
		</form>
		
		<div id="resultList">
			<c:forEach items="${sMap.postList}" var="postVo">
					<table class="searchResult" style="width: 800px;">
						<colgroup>
							<col style="width: 20%;">
							<col style="width: 80%;">
						</colgroup>
						<tr>
							<td class="text-center">${postVo.regDate}</td>
							<td><a href="${pageContext.request.contextPath}/${postVo.id}?c=${postVo.cateNo}&p=${postVo.postNo}">${postVo.postTitle}</a></td>
						</tr>
						<tr>
							<td class="text-center">${postVo.userName}(${postVo.id})</td>
							<td><a href="${pageContext.request.contextPath}/${postVo.id}?c=${postVo.cateNo}&p=${postVo.postNo}">${postVo.postContent}</a></td>
						</tr>
					</table>
			</c:forEach>
			
		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->
	
	
	</div>
	<!-- //center-content -->
</body>

</html>