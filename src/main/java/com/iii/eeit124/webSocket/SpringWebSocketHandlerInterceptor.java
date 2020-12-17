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
            if (member != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息            	            	
                String userName = (String) member.getName();  //一般直接保存user实体
                if (userName!=null) {
                	//使用者登入的名子
                    attributes.put("WEBSOCKET_USERID",userName);
                }else {
                	attributes.put("WEBSOCKET_USERID",session.getAttribute("SESSION_USERNAME"));
                } 
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes); 
    }
 
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
 
}
