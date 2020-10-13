<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <jsp:useBean id="SYSTEM" class="_00_init.util.GlobalService" scope="application"/> --%>

<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<c:set var="AppName" value="${SYSTEM.systemName}" scope="application"/>
<title>${AppName}</title>
</head>
<body><!-- <a href='abcdefg'>Abc</a> -->
${SYSTEM.systemName}<br>
${AppName}<br>
<!-- 設定變數funcName的值為"IND", top.jsp會使用此變數-->

<c:set var="funcName" value="IND" scope="session"/>
<!-- 引入共同的頁首 -->
<%-- <jsp:include page="/fragment/top.jsp" /> --%>
 <CENTER> 
 <h1>
 歡迎光臨<BR><FONT COLOR='RED'>${AppName}</FONT><BR>
 天天有優惠，天天有好康<BR>
 今天舉辦<FONT COLOR='BLUE'>滿佰送仟</FONT>的促銷活動<BR>
  </h1> 
 <BR>
  <h2>
 本店絕不販賣盜版書、山寨書與過期書
 </h2>
   <HR>
   <p style='font-family: "Verdana"; color: red'>
  本範例需要先執行_00_init.EDMTableReset.java來建立初始資料 
 </p>
 <!-- 
 ${logoutMessage}:
 為Logout.jsp所放入的感謝訊息
  
 ${MsgOK.InsertOK}:
 _01_register.controller.RegisterSevlet.java放入此訊息
 會員註冊成功會顯示此頁，並透過session物件送來識別字串為MsgOK
 的Map物件，透過MsgOK.InsertOK取出註冊成功的訊息  
 -->
 ${logoutMessage}${MsgOK.InsertOK}<BR>
 <BR>  
 <% // 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
    session.removeAttribute("MsgOK");  
 %>
 </CENTER> 
</body>
</html>