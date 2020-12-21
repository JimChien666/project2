package com.iii.eeit124.webSocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.iii.eeit124.entity.Members;

public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            Members member=(Members)session.getAttribute("LoginOK");
            String userName = (String) session.getAttribute("SESSION_USERNAME");  //一般直接儲存user實體
            
            //getAttribute("LoginOK")!=null <=意思就是有登入
			if (member != null) {
				// 使用userName区分WebSocketHandler，以便定向发送消息
				userName = String.valueOf(member.getId()); // 抓登入的會員名字
				attributes.put("WEBSOCKET_USERID", userName);
			} else {
				attributes.put("WEBSOCKET_USERID", userName);
			}
        }
        
        
        
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//                //使用userName區分WebSocketHandler，以便定向傳送訊息
//                String userName = (String) session.getAttribute("SESSION_USERNAME");  //一般直接儲存user實體
//                if (userName!=null) {
//                    attributes.put("WEBSOCKET_USERID",userName);
//                }
//
//            }
//        }        
        return super.beforeHandshake(request, response, wsHandler, attributes); 
    }
 
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
 
}
