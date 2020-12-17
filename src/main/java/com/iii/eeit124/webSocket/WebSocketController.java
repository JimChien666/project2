package com.iii.eeit124.webSocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.iii.eeit124.entity.Members;

@Controller
public class WebSocketController {
	//这个注解会从Spring容器拿出Bean
    @Bean  
    public SpringWebSocketHandler infoHandler() {
 
        return new SpringWebSocketHandler();
    }
 
 
    @RequestMapping("/websocket/loginPage")
    public String loginPage(@RequestParam String memberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
        Members member=(Members)session.getAttribute("LoginOK");
        session.setAttribute("memberId",memberId); //把商品詳細頁面的memberId帶出來，讓商品有問題的人詢問
        if(member==null) {
        	return "order/login";
        }else {
            String username = member.getName();
            session.setAttribute("SESSION_USERNAME", username);        	
        	return "order/send";
        }
    }
 
 
    @RequestMapping("/websocket/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username+"登入");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username); //一般直接保存user实体
        return "order/send";
    }
 
    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        infoHandler().sendMessageToUser(username, new TextMessage("你好，測試！！！！"));
        return null;
    }
 
 
    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad() {
        infoHandler().sendMessageToUsers(new TextMessage("發送測試訊息!"));
        System.out.println("群发成功");
        return "broad";
    }
 
}
