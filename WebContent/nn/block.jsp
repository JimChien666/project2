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
  background-image: url("");
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
  background-image: url("");
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
  		<div class="top"></div>
 			 <div class="bottom">
    			<div class="headpic">
     			 <div class="name">${attractionIntroduction}</div>
    			</div>
  	</div>
</div>