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
            /*alert(openEvt.Data);*/
        }
 
        function onMessage(evt) {
            /*alert("管理員發送訊息:" + evt.data);*/
            /*console.log(evt.data);*/
            var memberId="'"+(evt.data).split("#燚#")[0]+"'";
            var message=(evt.data).split("#燚#")[1];
			if (document.getElementById('sendBtn')){
	            document.getElementById('sendBtn').innerHTML='<input type="button" onclick="doSendUser('+memberId+');" value="123"/>';				
			}else{
				document.getElementById('search-content').innerHTML='<div class="alert alert-info" role="alert" onclick="goOrderPage()">'+message+'</div>';
			}
            
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
	                /*alert("發送成功!");*/
	            } else {
	                alert("連接失敗!");
	            }
        }

        function doSendCreateOrder(memberId) {
        /*alert(websocket.readyState + ":" + websocket.OPEN);*/
            if (websocket.readyState == websocket.OPEN) {
                var msg = "您有一筆新的訂單";
                websocket.send("#anyone#"+memberId+"#燚#"+msg);//调用后台handleTextMessage方法
                
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

		function goOrderPage(){
			window.location.href = "/team6/order/SellingOrderList";
			dcoument.getElementById("search-content").innerHTML="";
		}