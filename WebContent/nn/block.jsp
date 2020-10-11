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
  		</div>
  	</div>
</div>