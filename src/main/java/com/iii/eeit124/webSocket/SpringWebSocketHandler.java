package com.iii.eeit124.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.iii.eeit124.entity.Members;

public class SpringWebSocketHandler extends TextWebSocketHandler {

 
    private static final Map<String, WebSocketSession> users;  //Map来存储WebSocketSession，key用USER_ID 即在线用户列表
 
    //用户标识
    private static final String USER_ID = "WEBSOCKET_USERID";   //对应监听器从的key
 
 
    static {
        users =  new HashMap<String, WebSocketSession>();
    }
 
    public SpringWebSocketHandler() {
    	
    	
    }
 
    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
 
        System.out.println("成功建立websocket连接!");
        
        String userId = (String) session.getAttributes().get(USER_ID);

        users.put(userId,session);
        System.out.println("當前線上用戶數量:"+users.size());
 
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
//        TextMessage returnMessage = new TextMessage("成功建立socket连接，你将收到的离线");
//        session.sendMessage(returnMessage);
    }
 
    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	
        String userId= (String) session.getAttributes().get(USER_ID);
        System.out.println("用户"+userId+"已退出！");
        users.remove(userId);
        System.out.println("剩餘線上用户"+users.size());
    }
 
    /**
     * js调用websocket.send时候，会调用该方法
     * 
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
 
        super.handleTextMessage(session, message);
        //自己
        String userId = (String) session.getAttributes().get(USER_ID);
        /**
         * 收到消息，自定义处理机制，实现业务
         */
        System.out.println("服務器收到消息："+message);
        //1:1
        if(message.getPayload().startsWith("#anyone#")){ //单发某人
        	 String result = message.getPayload();
        	 //發送對象
        	 String memberId = result.split("#anyone#")[1].split("#燚#")[0];
        	 String reply = result.split("#燚#")[1];
//        	 sendMessageToUser((String)session.getAttributes().get(USER_ID), new TextMessage("服務器群發：" +message.getPayload())) ;
             sendMessageToUser(memberId, new TextMessage(userId+"#燚#"+reply));
        //群發
        }else if(message.getPayload().startsWith("#everyone#")){
 
             sendMessageToUsers(new TextMessage("伺服器群發："+message.getPayload()));
 
        }else{
 
        }
 
    }
 
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("傳輸出現異常，關閉websocket連接... ");
        String userId= (String) session.getAttributes().get(USER_ID);
        users.remove(userId);
    }
 
    public boolean supportsPartialMessages() {
 
        return false;
    }
 
 
    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
    	
        for (String id : users.keySet()) {
        	System.out.println(id);
        	//
            if (id.equals(userId)) {
                try {
                    if (users.get(id).isOpen()) {
                        users.get(id).sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (String userId : users.keySet()) {
            try {
                if (users.get(userId).isOpen()) {
                    users.get(userId).sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
}
