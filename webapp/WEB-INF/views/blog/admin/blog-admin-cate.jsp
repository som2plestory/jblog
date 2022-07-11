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
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" value=""></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript"> 

$(document).ready(function(){
	fetchList()
})

//일련번호(view)
var n

function fetchList(){
	n = 0
	
	$.ajax({
		url : "${pageContext.request.contextPath}/admin/category/cateList",		
		type : "post",
		contentType : "application/json",
		dataType : "json",
		
		success : function(cateList){
				
			for(var i=0; i<cateList.length; i++){
				render(cateList[i])	
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
			
		}
	})
}


function render(cateVo){
	console.log("render()")
	n = n+1
	
	var str = ''
	str += '<tr id="t'+n+'">'
	str += '	<td>'+n+'</td>'
	str += '	<td>'+cateVo.cateName+'</td>'
	str += '	<td>'+cateVo.countPost+'</td>'
	str += '	<td>'+cateVo.desc+'</td>'
	str += '	<td class="text-center">'
	
	if(n != 1){
		str += '		<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"'
		str += '		 data-cateno="'+cateVo.cateNo+'" data-countpost="'+cateVo.countPost+'"'
		str += '		 data-no="'+n+'">'
	}
	
	str += '	</td>'
	str += '</tr>'
	
	$("#cateList").prepend(str)
}


$("#btnAddCate").on("click", function(){
	console.log("카테고리추가 버튼 클릭")
	
	var cateName = $("[name = 'name']").val()
	
	if(cateName == "" || cateName == null){
		alert("카테고리명을 입력해주세요.")
		return false
	}
	
	var desc = $("[name = 'desc']").val()
	
	var cateVo = {
		cateName: cateName,
		desc: desc
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/admin/category/insert",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cateVo),
		dataType : "json",
		
		success : function(cateVo1){
			render(cateVo1)
			
			$("[name = 'name']").val("")
			$("[name = 'desc']").val("")
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
			
		} 
	})
})


$("#cateList").on("click", ".btnCateDel", function(){
	console.log("카테고리 삭제 버튼 클릭")
	
	var $this = $(this)
	var no = $this.data("no")
	var cateNo = $this.data("cateno")
	var countPost = $this.data("countpost")
	console.log("no: "+no)
	console.log("cateNo: "+cateNo)
	console.log("countPost: "+countPost)
	
	if(countPost > 0){
		alert("해당 카테고리에 포스트가 존재하여 삭제할 수 없습니다.")
		return false
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/admin/category/delete",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(cateNo),
		dataType : "json",
	
		success : function(result){
			console.log(result)
			
			if(result == "success"){
				for(var i=1; i<=no; i++){
					$("#t"+i).remove()
				}
				fetchList()
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
			
		} 
	})
})


</script>

</html>