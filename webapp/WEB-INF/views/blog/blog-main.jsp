<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

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
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					<c:choose>
						<c:when test="${blogVo.logoFile == 'basic'}">
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>
							<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
						</c:otherwise>
					</c:choose>
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateIndex}" var="cateVo">
							<li>
								<a href="${pageContext.request.contextPath}/${blogVo.id}?c=${cateVo.cateNo}&p=${postVo.postNo}">
									${cateVo.cateName}
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<c:choose>
					<c:when test="${!empty postVo}">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${postVo.postTitle}</strong></div>
							<div id="postDate" class="text-left"><strong>${postVo.regDate}</strong></div>
							<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
						</div>
						<div id="post" >
							${postVo.postContent}
						</div>
					</c:when>
				
					<c:otherwise> 
						<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
									<div id="postDate" class="text-left"><strong></strong></div>
									<div id="postNick"></div>
						</div>
						<div id="post" >
						</div>
					</c:otherwise>
				</c:choose>
				<div id="cmtBox" class="clearfix">
					<c:if test="${!empty authUser}">
						<fieldset>
							<div>${authUser.userName}</div>
							<div><input type="text" name="cmtContent" value=""></div>
							<div><button id="btnAddCmt" class="btn_l" type="submit" >저장</button></div>
						</fieldset>
					</c:if>
				</div>
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${postList}" var="post">
							<tr>
								<td class="text-left">
									<a href="${pageContext.request.contextPath}/${blogVo.id}?c=${post.cateNo}&p=${post.postNo}">
										${post.postTitle}
									</a>
								</td>
								<td class="text-right">${post.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			
			</div>
			<!-- //post_area -->
			
			
		</div>	
		<!-- //content -->
		
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">



</script>

</html>