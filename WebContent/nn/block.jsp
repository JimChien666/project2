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
}

.bottom {
  width: 100%;
  height: 200px;
  background: white;
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

.name {
  right: -110px;
  top: 10px;
  color: white;
  letter-spacing: 2px;
}

</style>

	<div class="phone">
  		<div class="top" style='background-image: url("${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${attractionIntroduction.getCoverFileId()}");'></div>
  			
 			 <div class="bottom">
    			<div class="headpic" style='background-image: url("${pageContext.servletContext.contextPath}/nn/service/RetrieveImageServlet?id=${attractionIntroduction.getContentFileId()}");'>
     			 <div class="name">${attractionIntroduction.getName()}</div>
    			</div>
  	</div>
</div>