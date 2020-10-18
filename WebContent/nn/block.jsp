<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
@charset "UTF-8";
* {
  font-family: 微軟正黑體;
  position: relative;
}


.phone {
  width: 300px;
  /* border: solid 1px #888; */
  border-radius: 10px;
}

.top {
  width: 100%;
  height: 150px;
  /* background-image: url("${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${attractionIntroduction.getCoverFileId()}"); */
  background-size: cover;
  border: 1px solid black;
  background-position: center center;
  border-radius: 10px 10px 0px 0px;
}

.bottom {
  width: 100%;
  height: 200px;
  background: white;
  border: 1px solid black;
  border-radius: 0px 0px 10px 10px;
}

.headpic {
  width: 80px;
  height: 80px;
  position: absolute;
  top: -50px;
  left: 20px;
  /* background-image: url("https://scontent.ftpe8-2.fna.fbcdn.net/v/t1.0-9/1385703_10202411620279813_1569948950_n.jpg?_nc_cat=103&_nc_sid=f9d7a1&_nc_ohc=RgYi4fFpvHMAX9OuXsR&_nc_ht=scontent.ftpe8-2.fna&oh=97c394bfe7f411932ac0052f75606a42&oe=5FA7C510"); */
  background-size: cover;
  border: solid 2px white;
  border-radius: 50%;
}
.phone:hover {
	opacity : 0.6;
}

.name {
  right: -110px;
  width: 160px;
  top: 10px;
  color: white;
  letter-spacing: 2px;
}

.blockintro{
	position: absolute;
	width: 300px;
	top: 100px;
}

</style>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

function like(id) {
	$.ajax({
        type:"POST",
        url:"${pageContext.servletContext.contextPath}/nn/controler/AttractionLikeServlet?id=" + id,
        success:function (result) {
            if("yes"==result){
                alert("登入成功！");
            }else{
                alert("使用者名稱或密碼錯誤");
                $("#password").val("");  //將密碼input清空
                $("#password").focus();  //將游標定位到密碼input
            }
        },
        error:function (err) {
            alert("系統錯誤-loginPage.jsp-ajax");
        }
    });
	
}

</script>
<div class="col-lg-4">
	<div class="phone">
  		<div class="top" style='background-image: url("${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${attractionIntroduction.getCoverFileId()}");'></div>
  			
 			 <div class="bottom">
    			<div class="headpic" style='background-image: url("${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${attractionIntroduction.getContentFileId()}");'>
     			 <div class="name">${attractionIntroduction.getName()}</div>
     			 	<div class="blockintro">
					
     			 	<svg width="0.8em" height="0.8em" viewBox="0 0 16 16" class="bi bi-cursor-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M14.082 2.182a.5.5 0 0 1 .103.557L8.528 15.467a.5.5 0 0 1-.917-.007L5.57 10.694.803 8.652a.5.5 0 0 1-.006-.916l12.728-5.657a.5.5 0 0 1 .556.103z"/>
					</svg>
     			 	${attractionIntroduction.getCity()}${attractionIntroduction.getAddress()}<br/><br/>
     			 	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-telephone-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M2.267.98a1.636 1.636 0 0 1 2.448.152l1.681 2.162c.309.396.418.913.296 1.4l-.513 2.053a.636.636 0 0 0 .167.604L8.65 9.654a.636.636 0 0 0 .604.167l2.052-.513a1.636 1.636 0 0 1 1.401.296l2.162 1.681c.777.604.849 1.753.153 2.448l-.97.97c-.693.693-1.73.998-2.697.658a17.47 17.47 0 0 1-6.571-4.144A17.47 17.47 0 0 1 .639 4.646c-.34-.967-.035-2.004.658-2.698l.97-.969z"/>
					</svg>
					${attractionIntroduction.getTel()}
     			 	</div>
     			 	
    			</div>
    			<div id=${attractionIntroduction.getId()} style="right:0px; margin-left: 100px; cursor: pointer; width: 1em;" onclick="like(${attractionIntroduction.getId()})">
    			<button type="button" class="btn btn-danger"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						  <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
						</svg></button>
    			
				
				</div>
  		</div>
  	</div>
</div>