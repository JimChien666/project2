<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<title>加入會員</title>
<style type="text/css">
<!--
body {
	background-attachment: fixed;
	background-color: #EBFFEB;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}
.myBorder {
	color:#FFFF99;
	border: thin dotted #FFFFFF;
}
h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:8em;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:#F6E497;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.9em; 
}
/* 主要內容的區塊 */
#content {
  width: 700px ;
  margin-left: auto ;
  margin-right: auto ;
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A9A9A9;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a9a9a9;
}

#errorMsg {
    position:relative;
    top:0px; 
    left:0px;    
	color:#FF0000;
	font-size:0.8em;
}
-->
</style>
<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId(){   
	 document.forms[0].mid.focus();   // 將游標放在mid欄位內
}
</script>
</head>
<body onLoad="setFocusToUserId()" >
<c:set var="funcName" value="REG" scope="session"/>
<!-- 引入共同的頁首 -->
<jsp:include page="../nn/top.jsp" />
  <div id="content"> 
  <Table style="background-color: #E7CDFF; cellspacing:0; border:2px solid black; " >
     <TR height="60" >
         <TD>
         <TABLE style="cellspacing:1; " >
         <TR>
             <TD width="770" colspan='3' align="center" >
                 <Font color="#006600" size='5' face="標楷體">${AppName}</Font>
             </TD>
         </TR>
         <TR>
             <TD width="270" ></TD>
             <TD width="230"  align="center">
                 <Font color="#006600" size='4' face="標楷體">加入會員</Font>
             </TD>
            
         <!-- 此區塊顯示程式執行後的訊息 -->
             <TD width="270" align="left"><font size="-1" color="#FF0000">
                 ${MsgMap.InsertNG}${MsgMap.errorSaveData}</font>
             </TD>
         </TR>
         <TR>          
          <TD width="770" colspan='3'  align="center">
                 <Font color="red" size='2' face="標楷體">${MsgMap.passwordError}</Font>
             </TD>
          </TR>                    
         </TABLE>
       
     <TR><TD colspan="3">
  <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='register.do' />"  id="register.do" >
  	  <label class="fontSize" >身份：</label>
 	  <input type="radio" name="type" value="領養人" checked="true"> 領養人
	  <input type="radio" name="type" value="收容所"> 收容所<br/>
      <label class="fontSize" >帳號：</label>
      <input type="text" name="mid" value="${param.mid}" class="fieldWidth" style="width: 180px;">
      <!-- 
         注意value屬性的內容以及顯示錯誤訊息的寫法
      -->
      <font size="-1" color="#FF0000">${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >密碼：</label>
      <input type="password" name="password" value="${param.password}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPasswordEmpty}</font>      
      <br/>
      
      
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="password2" value="${param.password2}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPassword2Empty}</font>            
      <br/>
      
      <label class="fontSize" >姓名：</label>
      <input type="text" name="name" value="${param.name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>
      <label class="fontSize" >性別：</label>
 	  <input type="radio" name="sex" value="男" checked="true"> 男
	  <input type="radio" name="sex" value="女"> 女<br/>
      <label class="fontSize" >地址：</label>
      <input type="text" name="address" value="${param.address}"  class="fieldWidth" style="width: 320px;">
      <font color="red" size="-1">${MsgMap.errorAddr}</font>
      <br/>
      
      <label class="fontSize" >電話：</label>
      <input type="text"  name="tel" value="${param.tel}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>
      
      <label class="fontSize" >電子郵件：</label>
          <input type="text"  name="eMail" value="${param.eMail}"   class="fieldWidth" style="width: 300px;">
          <font color="red" size="-1">${MsgMap.errorEmail}</font>
      <br/>
      <label class="fontSize" >收入：</label>
      <input type="text" name="income" value="${param.income}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorIncome}</font>      
      <br/>
      
      <label class="fontSize" >照片：</label>
      <Input Type="file" size="40" class="fieldWidth" style="width: 480px;"  name="file1"><BR>
      <br/>
      <div id="btnArea" align="center">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</TD>
</TR>
</Table>
</div>
</body>
</html>