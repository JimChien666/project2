package com.iii.eeit124.webSocket.controller;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.webSocket.service.WSMessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
    //websocket服务层调用类
    @Autowired
    private WSMessageService wsMessageService;

  //请求入口
    @RequestMapping(value="/TestWS",method=RequestMethod.GET)
    @ResponseBody
//    public String TestWS(@RequestParam(value="userId",required=true) Long userId,
	public String TestWS(@RequestParam(value="userId",required=true) Long userId,
        @RequestParam(value="message",required=true) String message){
//        logger.debug("收到發送請求，向用户{}的消息：{}",userId,message);
        if(wsMessageService.sendToAllTerminal(userId, message)){
            return "發送成功";
        }else{
            return "發送失敗";
        }   
    }
}