<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Pet Me~陪你! 客服系統 </title>
    <!--
	<link rel="stylesheet" href="/css/style.css"/>
    -->
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script> -->
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script> -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.js"></script>

 <style>
.btncls{
	background-color: #7E4C4F; /* Green */
    border-radius: 10px;
    border: 8px;
    color: white;
    padding: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 18px;
    transition-duration: 0.3s;
    cursor: pointer;
    width:100px;
}
button.btncls:hover{
	background-color: #000000;
}

.msg1{
	background-color: #7E4C4F; /* Green */
    border-radius: 10px;
    border: 8px;
    color: white;
    padding: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 18px;
    transition-duration: 0.3s;
    cursor: pointer;
    width:100px;
}
</style>

</head>
<jsp:include page="../fragments/links.jsp" />
<body>
	<div  id="messageBox" style="height:300px; width:100%; border:1px black solid; overflow:auto; font-size:22px;" >
	</div>

	請輸入訊息：<input type="text" id="inputMsg" name="inputMsg" style="width:80%;">
	<span>
		<input  type="button" id="sendBtn" style="width:70px;" class="btncls" onclick="doSendUser(${memberId});" value="傳送"/>
	</span>
	<input class="btncls" type="button" onclick="message1();" value="1"/>
	<input class="btncls" type="button" onclick="message2();" value="2"/>
	<input class="btncls" type="button" onclick="message3();" value="3"/>
	<input class="btncls" type="button" onclick="message4();" value="4"/>

<!-- 	<input type="button" onclick="doSendUsers();" value="群發"/> -->
<!-- 	<input type="button" onclick="websocketClose();" value="關閉連接"/> -->

    <script>
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/team6/websocket/socketServer");
        }
        else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://localhost:8080/team6/websocket/socketServer");
        }
        else {
            websocket = new SockJS("http://localhost:8080/team6/sockjs/socketServer");
        }
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;
 
        function onOpen(openEvt) {
//             alert(openEvt.Data);
        }
 
        function onMessage(evt) {
//             alert("發送訊息:" + evt.data);
            console.log(evt.data);
            var memberId="'"+(evt.data).split("#燚#")[0]+"'";
            var message=(evt.data).split("#燚#")[1];
            document.getElementById('sendBtn').innerHTML='<input type="button" value="傳送訊息" onclick="doSendUser('+memberId+');" />';
            
            console.log(memberId);
            console.log(message);
            
            document.getElementById("messageBox").innerHTML+=" 對方:"+message+"<br/>" ;
        }
        function onOpen() {
        	
        }
        function onError() {}
        function onClose() {}
 
        function doSendUser(memberId) {
// 	        alert(websocket.readyState + ":" + websocket.OPEN);
	            if (websocket.readyState == websocket.OPEN) {
	                var message = document.getElementById("inputMsg").value;
	                console.log(message);
	                websocket.send("#anyone#"+memberId+"#燚#"+message);//调用后台handleTextMessage方法
	                document.getElementById("messageBox").innerHTML+= "<div align='right'><span  class='msg1'> 我:"+message+"</span></div>" ;
// 	                alert("發送成功!");

	            } else {
	                alert("連接失敗!");
	            }
        }
 
 
        function doSendUsers() {
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#everyone#"+msg);//调用后台handleTextMessage方法
//                 alert("發送成功!");
            } else {
                alert("連接失敗!");
            }
        }
 
 
        window.close=function()
        {
            websocket.onclose();
        }
        function websocketClose() {
        	websocket.close();
        }
        
        function message1() {
            document.getElementById("messageBox").innerHTML+="<div align='right'><span  class='msg1' > 我: 你好，我有問題想請教~</span></div>" ;
        }
        function message2() {
            document.getElementById("messageBox").innerHTML+="<div align='right'><span  class='msg1' > 我: 麻煩請說</span></div>" ;
        }
        function message3() {
            document.getElementById("messageBox").innerHTML+="<div align='right'><span  class='msg1' > 我: 請問此項商品有現貨嗎?</span></div>" ;
        }        
        function message4() {
            document.getElementById("messageBox").innerHTML+="<div align='right'><span  class='msg1' > 我: 有的，若有需要請直接下單。感謝您~</span></div>" ;
        }
    </script>
 <jsp:include page="../fragments/allJs.jsp" />    
</body>
</html>