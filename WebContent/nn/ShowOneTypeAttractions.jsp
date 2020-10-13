<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row"  style=" padding: 30px; border-radius: 10px;">
  		  <c:forEach items="${attractionIntroductionList}" var="attractionIntroduction" varStatus="id">
	      <div class="col-lg-4">
	        <div class="phone">
			  		<div class="top" style="background-image: '<c:url value='/nn/images/狗狗1.jpeg' />'"></div>
			 			 <div class="bottom">
			    			<div class="headpic">
			     			 <div class="name">${attractionIntroduction.getName()}</div>
			    			</div>
			  	</div>
			</div>
	      </div>
	      </c:forEach>
    	</div>