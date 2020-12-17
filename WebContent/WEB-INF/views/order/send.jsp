<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <!--
	<link rel="stylesheet" href="/css/style.css"/>
    -->
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script> -->
<!--     <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script> -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.js"></script>

 
</head>
<body>

	請輸入訊息：<textarea rows="5" cols="30" id="inputMsg" name="inputMsg" ></textarea>
	<input id="sendBtn" type="button" onclick="doSendUser(${memberId});" value="123"/>
	
	<button type="button" οnmouseover="doSendUser(${memberId});">發送</button>
	<button οnclick="doSendUsers();">群發</button>
	<button οnclick="websocketClose();">關閉連接</button>



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
            alert(openEvt.Data);
        }
 
        function onMessage(evt) {
            alert("管理員發送訊息:" + evt.data);
            console.log(evt.data);
            var memberId=(evt.data).split("#燚#")[0];
            var message=(evt.data).split("#燚#")[1];
            document.getElementById('sendBtn').onclick =  doSendUser2(memberId) 
            
            console.log(memberId);
            console.log(message);
        }
        function onOpen() {
        	
        }
        function onError() {}
        function onClose() {}
 
        function doSendUser(memberId) {
	        	alert("fuck");
	        alert(websocket.readyState + ":" + websocket.OPEN);
	            if (websocket.readyState == websocket.OPEN) {
	                var msg = document.getElementById("inputMsg").value;
	                console.log(msg);
	                websocket.send("#anyone#"+memberId+"#燚#"+msg);//调用后台handleTextMessage方法
	                alert("發送成功!");
	            } else {
	                alert("連接失敗!");
	            }
        }
 
 
        function doSendUsers() {
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#everyone#"+msg);//调用后台handleTextMessage方法
                alert("發送成功!");
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
    </script>
</body>
</html>