package com.iii.eeit124.webSocket.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.iii.eeit124.webSocket.controller.HandShake;
import com.iii.eeit124.webSocket.controller.MyTextWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

//	@Resource
//	MyWebSocketHandler handler;
	@Resource
	MyTextWebSocketHandler textHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		/*
		registry.addHandler(handler, "/ws").addInterceptors(new HandShake());
		//sock js處理類
		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
		*/
		
		registry.addHandler(textHandler, "/ws").addInterceptors(new HandShake());
		//sock js處理類
		registry.addHandler(textHandler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
	}
	
}
