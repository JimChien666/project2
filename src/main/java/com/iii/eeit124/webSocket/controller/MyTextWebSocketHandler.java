package com.iii.eeit124.webSocket.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.iii.eeit124.webSocket.WebsocketDemo;

//import cc.hu.test.websocket.common.User;
//import com.iii.eeit124.webSocket.controller.MainController;

/**
 * @author root
 *
 */
@Component
public class MyTextWebSocketHandler extends AbstractWebSocketHandler {
	
	public static Map<Long, WebSocketSession> userWebsocketSessionMap;
	
	static {
		userWebsocketSessionMap = new HashMap<Long, WebSocketSession>();
	}
//	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		Long uid = (Long) session.getAttributes().get("uid");
//		userWebsocketSessionMap.put(uid, session);
//		User u = MainController.loginUserMap.get(uid);
//		CCMsg welcome = new CCMsg();
//		welcome.setTo(uid);
//		welcome.setMsg("[系統訊息]歡迎[" + u.getNickName() + "]進入聊天");
//		welcome.setMsgType(MsgType.CHAT_MSG);
//		welcome.setFromName("系統");
//		welcome.setSendDate(new Date());
//		welcome.setFrom(0L);
//		session.sendMessage(new TextMessage(JSON.toJSONString(welcome)));
//		//群發通知線上列表更新
//		sendOnlineUserMsg();
//	}
//
//	@Override
//	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//		if (session.isOpen())
//			session.close();
//		Iterator<Entry<Long, WebSocketSession>> mapItr = userWebsocketSessionMap.entrySet().iterator();
//		while (mapItr.hasNext()) {
//			final Entry<Long, WebSocketSession> one = mapItr.next();
//			Long key = one.getKey();
//			WebSocketSession value = one.getValue();
//			if (value.getId().equals(session.getId())) {
//				mapItr.remove();
//				System.out.println("傳送訊息錯誤, 移除socket[key:" + key + "\t" + MainController.loginUserMap.get(key));
//				//移除線上使用者
//				MainController.loginUserMap.remove(key);
//				break;
//			}
//		}
//		exception.printStackTrace();
//		//群發通知線上列表更新
//		sendOnlineUserMsg();
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		System.out.println("websocket使用者關閉:" + JSON.toJSONString(status));
//		Iterator<Entry<Long, WebSocketSession>> mapItr = userWebsocketSessionMap.entrySet().iterator();
//		while (mapItr.hasNext()) {
//			final Entry<Long, WebSocketSession> one = mapItr.next();
//			Long key = one.getKey();
//			WebSocketSession value = one.getValue();
//			if (value.getId().equals(session.getId())) {
//				mapItr.remove();
//				System.out.println("使用者關閉, 移除socket[key:" + key + "\t" + MainController.loginUserMap.get(key));
//				MainController.loginUserMap.remove(key);
//				break;
//			}
//		}
//		//群發通知線上列表更新
//		sendOnlineUserMsg();
//	}
//
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		if (message.getPayloadLength() == 0)
//			return;
//		System.out.println("[收到訊息====>]" + message.getPayload().toString());
//		CCMsg msg = JSON.parseObject(message.getPayload().toString(), CCMsg.class);
//		WebSocketSession toSession = userWebsocketSessionMap.get(msg.getTo());
//		if (toSession != null && toSession.isOpen()) {
//			msg.setSendDate(new Date());
//			toSession.sendMessage(new TextMessage(JSON.toJSONString(msg)));
//		} else  {
//			CCMsg welcome = new CCMsg();
//			welcome.setTo(msg.getFrom());
//			welcome.setMsg("[系統訊息]對方已下線,請稍後重試...");
//			welcome.setMsgType(MsgType.CHAT_MSG);
//			welcome.setFromName("系統");
//			welcome.setSendDate(new Date());
//			welcome.setFrom(0L);
//			session.sendMessage(new TextMessage(JSON.toJSONString(welcome)));
//			//群發通知線上列表更新
//			sendOnlineUserMsg();
//		}
//	}
	//傳送線上資訊更新訊息
//	public static void sendOnlineUserMsg() {
//		CCMsg msg = new CCMsg();
//		msg.setMsg(JSON.toJSONString(MainController.getOnLineUserList()));
//		msg.setMsgType(MsgType.ONLINE_USER_MSG);
//		msg.setFromName("系統");
//		msg.setSendDate(new Date());
//		msg.setFrom(0L);
//		Iterator<Entry<Long, WebSocketSession>> mapItr = userWebsocketSessionMap.entrySet().iterator();
//		while (mapItr.hasNext()) {
//			final Entry<Long, WebSocketSession> one = mapItr.next();
//			Long key = one.getKey();
//			msg.setTo(key);
//			WebSocketSession value = one.getValue();
//			try {
//				value.sendMessage(new TextMessage(JSON.toJSONString(msg)));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}
	
	//日志记录      
    private Logger logger = LoggerFactory.getLogger(WebsocketDemo.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
   
    //记录每个用户下多个终端的连接
    private static Map<Long, Set<MyTextWebSocketHandler>> userSocket = new HashMap<>();
 
    //需要session来对用户发送数据, 获取连接特征userId
    private Session session;
    private Long userId;
    
    

    /**
     * @Title: onOpen
     * @Description: websocekt连接建立时的操作
     * @param @param userId 用户id
     * @param @param session websocket连接的session属性
     * @param @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId,Session session) throws IOException{
        this.session = session;
        this.userId = userId;
        onlineCount++;
        
        
//        Members member=(Members)httpSession.getAttribute("LoginOK");
//        String memberName =null;
//        if( member == null) {
//     	  memberName ="";
//        }else {
//     	  memberName = member.getName();
//        }
        
        //根据该用户当前是否已经在别的终端登录进行添加操作
        if (userSocket.containsKey(this.userId)) {
            logger.debug("当前用户id:{}已有其他终端登录",this.userId);
            userSocket.get(this.userId).add(this); //增加该用户set中的连接实例
        }else {
            logger.debug("当前用户id:{}第一个终端登录",this.userId);
            Set<MyTextWebSocketHandler> addUserSet = new HashSet<>();
            addUserSet.add(this);
            userSocket.put(this.userId, addUserSet);
        }
        logger.debug("用户{}登录的终端个数是为{}",userId,userSocket.get(this.userId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }
   
    /**
     * @Title: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose(){
        //移除当前用户终端登录的websocket信息,如果该用户的所有终端都下线了，则删除该用户的记录
        if (userSocket.get(this.userId).size() == 0) {
            userSocket.remove(this.userId);
        }else{
            userSocket.get(this.userId).remove(this);
        }
        logger.debug("用户{}登录的终端个数是为{}",this.userId,userSocket.get(this.userId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }
   
    /**
     * @Title: onMessage
     * @Description: 收到消息后的操作
     * @param @param message 收到的消息
     * @param @param session 该连接的session属性
     */
    @OnMessage
    public void onMessage(String message, Session session) {    
    	
        logger.debug("收到来自用户id为：{}的消息：{}",this.userId,message);
        if(session ==null)  logger.debug("session null");
        sendMessageToUser(this.userId,message);	
    }
   
    /**
     * @Title: onError
     * @Description: 连接发生错误时候的操作
     * @param @param session 该连接的session
     * @param @param error 发生的错误
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("用户id为：{}的连接发送错误",this.userId);
        error.printStackTrace();
    }
   
  /**
   * @Title: sendMessageToUser
   * @Description: 发送消息给用户下的所有终端
   * @param @param userId 用户id
   * @param @param message 发送的消息
   * @param @return 发送成功返回true，反则返回false
   */
    public Boolean sendMessageToUser(Long userId2,String message){
        if (userSocket.containsKey(userId2)) {
            logger.debug(" 给用户id为：{}的所有终端发送消息：{}",userId2,message);
            for (MyTextWebSocketHandler WS : userSocket.get(userId2)) {
                logger.debug("sessionId为:{}",WS.session.getId());
                try {
                    WS.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.debug(" 给用户id为：{}发送消息失败",userId2);
                    return false;
                }
            }
            return true;
        }
        logger.debug("发送错误：当前连接不包含id为：{}的用户",userId2);
        return false;
    }
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
		onMessage(message.toString(),session);

	}
	
}