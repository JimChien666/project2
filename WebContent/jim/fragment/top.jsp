<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eDM.css" type="text/css" />

<script
	src="${pageContext.request.contextPath}/javascript/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/javascript/eDM.js"></script>

<c:set var='debug' value='true' scope='application' />
<table class='menuOuter'>
  <tr height="48px">
    <td width="80px" rowspan='2'>
      
    <img width="80px" height="60px"
      src="<c:url value='/jim/images/CatIndex.jpg'/>">  
      
    </td>
    <td>
      <table class='menuInner' > 
        <tr>
          <td class='menuData'>
            <div class='menu'>
			  <c:if test="${empty LoginOK}">
				<a href="<c:url value='/_02_login/login.jsp' />">
				     登入 
				</a>
              </c:if>
			</div>
		  </td>
		  <td class='menuData'>
			<div class='menu'>
			   <c:if test="${ funcName != 'SHO' }">
                  <a href="<c:url value='/ProductsPage' />" >
				        購物 
				  </a>
			   </c:if>
			   <c:if test="${ funcName == 'SHO' }"> 
                                                 購物
               </c:if>
			</div>
		  </td>
		  <td class='menuData'>
			<div class='menu'>
			  <c:if test="${ funcName != 'CHE' }">
			     <a href="<c:url value='/jim/InsertProduct.jsp' />">
				     新增商品 
				 </a>
			  </c:if>
			  <c:if test="${ funcName == 'CHE' }"> 
                                               新增商品 
              </c:if>
			</div>
		  </td>
		  <td class='menuData'>
			<div class='menu'>
			  <c:if test="${ funcName != 'ORD' }">
			     <a href="<c:url value='/jim/QueryProduct.jsp' />">
				   查詢商品
			     </a>
			  </c:if>
			  <c:if test="${ funcName == 'ORD' }"> 
                                                查詢商品
              </c:if>
			</div>
		  </td>
		  <td class='menuData'>
			<div class='menu'>
		      <c:if test="${ funcName != 'BMT' }">
				<a href="<c:url value='/_20_productMaintain/DisplayPageProducts' />">
                                             維護
                </a>
              </c:if>
			  <c:if test="${ funcName == 'BMT' }"> 
                                             維護
              </c:if>
			</div>
		  </td>
		  <td class='menuData'>
			<div class='menu'>
		       &nbsp;
			</div>
		  </td>
          
		  <td class='menuData'>
		  <div class='menu'>
			<c:if test="${ funcName != 'REG' }">
			   <a href="<c:url value='/_01_register/register.jsp' />"> 註冊 </a>
			</c:if> 
			<c:if test="${ funcName == 'REG' }"> 
                                                     註冊
            </c:if>
            </div>
          </td>
		  <td class='menuData'>
		  <div class='menu' style='width:50px;'>
			<c:if test="${ funcName != 'IND' }">
			   <a href="<c:url value='/index.jsp' />"> 回首頁 </a>
			</c:if>
			</div>
		  </td>
		  
		  <td class='menuData'>
		  <div class='menu'>
			<c:if test="${ ! empty LoginOK }">
			   <a href="<c:url value='/_02_login/logout.jsp' />">
  				登出 
	           </a>
			</c:if>
			</div>
		  </td>
		  <td class='menuData'>
           	<c:if test="${! empty LoginOK }">
               <img height='40px' width='30px'
	src='${pageContext.request.contextPath}/_00_init/getImage?id=${LoginOK.memberId}&type=MEMBER'>
	
			</c:if>
		  </td> 
		</tr>
		
<!-- 		<tr height='20px'> -->
<%-- 			<td width='300px' colspan='9'><small>${pageContext.session.id }</small> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
	  </table>
	<tr>
      <td>
	  <hr style="color: #f00; background-color: #f00; height: 2px;">
	  </td>
	</tr>
</table>