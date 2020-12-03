<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../fragments/links.jsp" />
<body>
 <jsp:include page="../fragments/headerArea.jsp" />
 <div class="breadcrumb-area pt-95 pb-95 bg-img"
  style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
  <div class="container">
   <div class="breadcrumb-content text-center">
    <h2>會員編輯</h2>
    <ul>
     <li><a href="<c:url value='/' />">首頁</a></li>
     <li><a href="<c:url value='/member/myAccount'/>">會員中心</a></li>
     <li class="active">會員編輯</li>
    </ul>
   </div>
  </div>
 </div>
 <jsp:include page="../members/fragments/myAccountHeaderArea.jsp" />

 <div class="container">
  <div class="row flex-row-reverse">
   <div class="col-lg-9 col-md-8">
    <div class="row">
     <div class="col-lg-6 col-md-12">
      <h3>會員資料</h3>
      <div class="blog-wrapper mb-30 gray-bg">
       <div class="blog-img hover-effect">
        <a href="#"><img alt=""
         src="<c:url value='/member/processFileReadAction.contoller?fileId=${LoginOK.getFileId()}' />"></a>
       </div>
       <div class="blog-content">
        <div class="blog-meta">
         <ul>
          <li>詳細資料</li>
         </ul>
        </div>
        <h4>
         <td>姓名：</td><td>${LoginOK.getName()}</td><br/>
         <td>身份：</td>
    <td>${LoginOK.getMemberType()}</td><br/>
    <td>性別：</td>
    <td>${LoginOK.getSex()}</td><br/>
    <td>電話：</td>
    <td>${LoginOK.getTel()}</td><br/>
    <td>地址：</td>
    <td>${LoginOK.getAddress()}</td><br/>
    <td>Email：</td>
    <td>${LoginOK.getEmail()}</td><br/>
    <td>帳號：</td>
    <td>${LoginOK.getAccount()}</td><br/>
        </h4>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
 

 <jsp:include page="../fragments/footerArea.jsp" />
 <jsp:include page="../fragments/allJs.jsp" />
</body>
</html>