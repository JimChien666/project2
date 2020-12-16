package com.iii.eeit124.webSocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
* @ServerEndpoint 註解是一個類層次的註解，它的功能主要是將目前的類定義成一個websocket伺服器端,
* 註解的值將被用於監聽使用者連線的終端訪問URL地址,客戶端可以通過這個URL來連線到WebSocket伺服器端
*/
@ServerEndpoint("/websocket")
public class WebSocketTest {
//靜態變數，用來記錄當前線上連線數。應該把它設計成執行緒安全的。
private static int onlineCount = 0;
//concurrent包的執行緒安全Set，用來存放每個客戶端對應的MyWebSocket物件。若要實現服務端與單一客戶端通訊的話，可以使用Map來存放，其中Key可以為使用者標識
private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
//與某個客戶端的連線會話，需要通過它來給客戶端傳送資料
private Session session;
/**
* 連線建立成功呼叫的方法
* @param session  可選的引數。session為與某個客戶端的連線會話，需要通過它來給客戶端傳送資料
*/
@OnOpen
public void onOpen(Session session){
this.session = session;
webSocketSet.add(this);     //加入set中
addOnlineCount();           //線上數加1
System.out.println("有新連線加入！當前線上人數為"  + getOnlineCount());
}
/**
* 連線關閉呼叫的方法
*/
@OnClose
public void onClose(){
webSocketSet.remove(this);  //從set中刪除
subOnlineCount();           //線上數減1
System.out.println("有一連線關閉！當前線上人數為"+getOnlineCount());
}
/**
* 收到客戶端訊息後呼叫的方法
* @param message 客戶端傳送過來的訊息
* @param session 可選的引數
*/
@OnMessage
public void onMessage(String message, Session session) {
System.out.println("來自客戶端的訊息:" + message);
//群發訊息
for(WebSocketTest item: webSocketSet){
try {
item.sendMessage(message);
} catch (IOException e) {
e.printStackTrace();
continue;
}
}
}
/**
* 發生錯誤時呼叫
* @param session
* @param error
*/
@OnError
public void onError(Session session, Throwable error){
System.out.println("發生錯誤");
error.printStackTrace();
}
/**
* 這個方法與上面幾個方法不一樣。沒有用註解，是根據自己需要新增的方法。
* @param message
* @throws IOException
*/
public void sendMessage(String message) throws IOException{
this.session.getBasicRemote().sendText(message);
//this.session.getAsyncRemote().sendText(message);
}
public static synchronized int getOnlineCount() {
return onlineCount;
}
public static synchronized void addOnlineCount() {
WebSocketTest.onlineCount++;
}
public static synchronized void subOnlineCount() {
WebSocketTest.onlineCount--;
}
}