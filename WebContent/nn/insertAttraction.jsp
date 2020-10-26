 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寵物旅遊</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

$(document).ready(function(){
  $.ajax({
        type:"POST",
        url:"${pageContext.servletContext.contextPath}/GetInsertPageDataServlet",
        success:function (result) {
            var json = JSON.parse(result); 
            console.log(json.citys);
            var citys = json.citys;
            var attractionTypes= json.attractionTypes;
            var tags = json.tags;
            for(var i=0; i<citys.length; i++){
            	if("${param.city}"==citys[i].name){
            		$("#city").append('<option selected="selected" value="'+ citys[i].id +'">'+ citys[i].name + '</option>');
               	}
            	else{
            		$("#city").append('<option value="'+ citys[i].id +'">'+ citys[i].name + '</option>');
                }
            }
            for(var i=0; i<attractionTypes.length; i++){
            	if("${param.type}"==attractionTypes[i].name){
            		$("#attractionType").append('<option selected="selected" value="'+ attractionTypes[i].id +'">'+ attractionType[i].name + '</option>');
               	}
            	else{
            		$("#attractionType").append('<option value="'+ attractionType[i].id +'">'+ attractionTypes[i].name + '</option>');
                }
            }
            for(var i=0; i<tags.length; i++){
            	$("#tag").append('<div class="form-check form-check-inline"><input name="tag" class="form-check-input" type="checkbox" value='+ tags[i].id+'><label class="form-check-label" for="inlineCheckbox1">'+ tags[i].name + '</label></div>');
            }
        },
        error:function (err) {
           
        }
	});
});
</script>
</head>
<body>
<jsp:include page="top.jsp" />
<div class="container" style="padding-top: 100px;">
<h2>
新增旅遊點
</h2>
<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='/nn/controler/CheckInsertAttractionServlet' />">
  <div class="form-group">
    <label for="exampleFormControlSelect1">新增類型</label><font color="red" size="-1">${MsgMap.errorType}</font>
    <select class="form-control" id="attractionType" name="type">
      <option disabled selected>- 選擇類型 -</option>
      <%-- <c:forEach items="${attractionTypeList}" var="attractionType" varStatus="id">
      	<c:if test="${param.type==attractionType.getId()}" >
      		<option selected="selected" value="${attractionType.getId()}">${attractionType.getName()}</option>
      	</c:if>
      	<c:if test="${param.type!=attractionType.getId()}" >
      		<option value="${attractionType.getId()}">${attractionType.getName()}</option>
      	</c:if>
      	
      </c:forEach> --%>

      
    </select>
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlInput1">名稱</label><font color="red" size="-1">${MsgMap.errorName}</font>
    <input type="text" name="name" value="${param.name}" class="form-control" id="exampleFormControlInput1">
    
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlFile1">門面照片</label><font color="red" size="-1">${MsgMap.errCoverImg}</font>
    <input type="file" name="coverimg" value="${param.coverimg}" class="form-control-file" id="exampleFormControlFile1">
    
  </div>

	
  <div class="form-group">
    <label for="exampleFormControlInput1">電子郵件</label><font color="red" size="-1">${MsgMap.errorEmail}</font>
    <input type="email" name="email" value="${param.email}" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    
  </div>
  
  <div class="form-row">
	  <div class="form-group col-md-3">
	      <label for="inputState">城市</label><font color="red" size="-1">${MsgMap.errorCity}</font>
	      <select name="city" id="city" class="form-control">
	          <option disabled selected>- 選擇類型 -</option>
		      <%-- <c:forEach items="${cityList}" var="city" varStatus="id">
		      	<c:if test="${param.city==city.getId()}" >
		      		<option selected="selected" value="${city.getId()}">${city.getName()}</option>
		      	</c:if>
		      	<c:if test="${param.city!=city.getId()}" >
		      		<option value="${city.getId()}">${city.getName()}</option>
		      	</c:if>
      		  </c:forEach> --%>
	      </select>
	    </div>
    <div class="col-9">
      <label for="exampleFormControlInput1">地址</label><font color="red" size="-1">${MsgMap.errorAddress}</font>
      <input type="text" name="address" value="${param.address}" class="form-control">
      
    </div>
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlInput1">電話</label><font color="red" size="-1">${MsgMap.errorTel}</font>
    <input type="text" name="tel" value="${param.tel}" class="form-control" id="exampleFormControlInput1">
    
  </div>
  
  <div>
  <label for="exampleFormControlInput1">熱門標籤(可複選)</label>
  </div>
  <div id="tag">
  <c:forEach items="${tagList}" var="tag" varStatus="id">
		  <div class="form-check form-check-inline">
			  <input name="tag" class="form-check-input" type="checkbox" value=${tag.getId()}>
			  <label class="form-check-label" for="inlineCheckbox1">${tag.getName()}</label>
			</div>
      	
  </c:forEach>
  </div>
	<div class="form-group">
	    <label for="exampleFormControlFile1">環境照片</label><font color="red" size="-1">${MsgMap.errContentImg}</font>
	    <input name="contentimg" value="${param.contentimg}" type="file" class="form-control-file" id="exampleFormControlFile1" accept="image/*,.pdf" multiple>
	</div>
	<div class="form-group">
	    <label for="exampleFormControlTextarea1">內容</label><font color="red" size="-1">${MsgMap.errorContent}</font>
	    <textarea name="content" class="form-control" id="exampleFormControlTextarea1" rows="3">${param.content}</textarea>
	    
	</div>
	<div class="col-6">
      <button  type="submit" name="submit" id="submit"  class="btn btn-primary mb-12">Submit</button>
    </div>
</form>


</div>








</body>
</html>