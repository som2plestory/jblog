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
					</c:otherwise>
				</c:choose>
				
				<div id="cmtBox">
					<c:if test="${!empty authUser && !empty postVo}">
						<fieldset>
							<input type="hidden" name="userNo" value="${authUser.userNo}"> 
							<div id="cmtName" class="text-center">${authUser.userName}</div>
							<div id="cmtContent" class="text-left"><input type="text" name="cmtContent" value=""></div>
							<div id="cmtAdd"><button id="btnAddCmt" type="submit" data-no="${postVo.postNo}">등록</button></div>
						</fieldset>
					</c:if>
					<div id="cmtList" class="text-left">
					</div>
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
var n = 0
var userNo = $("[name='userNo']").val()

$(document).ready(function(){
	fetchCmtList()
})


function fetchCmtList(){

	var post = $("#postTitle").text()
	
	if (post == "" || post == null){
		console.log("포스트 댓글 미존재")
		return false
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/${postVo.postNo}/cmtList",		
		type : "post",
		contentType : "application/json",
		dataType : "json",
		
		success : function(cmtList){
				
			for(var i=0; i<cmtList.length; i++){
				render(cmtList[i])	
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	})
}

function render(cmtVo){
	console.log("render()")
	n = n+1
	
	var str = ''
	str += '<div id="c'+n+'" class="cmtLine" style="height: 24px; padding: 10px 0px; border-top: 1px solid #BDBDBD;">'
	str += '	<span class="cmtUser">'+cmtVo.userName+'</span>'
	str += '	<span class="cmtCnt" class="text-left">'+cmtVo.cmtContent+'</span>'
	str += '	<span class="cmtDate" class="text-right">'+cmtVo.regDate+'</span>'
	str += '	<span class="cmtDel" class="text-center">'
	
	if(userNo == cmtVo.userNo ){
		str += '		<img class="btnCmtDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"'
		str += '				data-n="'+n+'" data-no="'+cmtVo.cmtNo+'">'
	}
	
	str += '	</span>'
	str += '</div>'
	
	$("#cmtList").prepend(str)
}


$("#cmtList").on("click", ".btnCmtDel",function(){
	console.log("코멘트 삭제 클릭")
	
	var $this = $(this)
	var cmtNo = $this.data("no")
	
	$.ajax({
		url : "${pageContext.request.contextPath}/comments/delete",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cmtNo),
		
		dataType : "json",
		success : function(result){
			console.log(result)
			
			if(result == "success"){
				$("#c"+n).remove()
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	})
})


$("#btnAddCmt").on("click", function(){
	console.log("코멘트 등록 클릭")
	
	var cmtContent = $("[name = 'cmtContent']").val()
	
	if(cmtContent == '' || cmtContent == null){
		alert("내용을 입력해주세요.")
		return false
	}
	
	var $this = $(this)
	var postNo = $this.data("no")
	var userNo = $("[name = 'userNo']").val()
	
	var cmtVo = {
		postNo : postNo,
		userNo : userNo,
		cmtContent : cmtContent
	}
	console.log(cmtVo)
	
	$.ajax({
		url : "${pageContext.request.contextPath}/comments/insert",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cmtVo),
		
		dataType : "json",
		success : function(cmtVo1){
			render(cmtVo1)
			$("[name = 'cmtContent']").val("")
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
			
		} 
	})
	
})


</script>

</html>