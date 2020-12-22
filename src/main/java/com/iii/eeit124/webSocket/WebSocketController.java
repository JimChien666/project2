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
    public String loginPage(@RequestParam(name="memberId",required = false) String memberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
        Members member=(Members)session.getAttribute("LoginOK");
        if(memberId!=null) {
        	session.setAttribute("memberId",memberId); //把商品詳細頁面的memberId設到Session，讓商品有問題的人詢問
        }
        if(member==null) {
        	//沒登入PetMe系統，則導入到登入頁面
        	return "websocket/login";
        }else {
            String userId = String.valueOf(member.getId());
            session.setAttribute("SESSION_USERNAME", userId);  //有登入，把ID設到   "SESSION_USERNAME"，Interceptor會把他抓出來   	
        	return "websocket/send";
        }
    }
 
 
    @RequestMapping("/websocket/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");  //login.jsp的username
        System.out.println(username+"登入");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username); //沒登入，把username設到   "SESSION_USERNAME" ，Interceptor會把他抓出來   	
        return "websocket/send";
    }
 
    
    //單發測試
    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        infoHandler().sendMessageToUser(username, new TextMessage("你好，測試！！！！"));
        return null;
    }
 
    //群發測試
    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad() {
        infoHandler().sendMessageToUsers(new TextMessage("發送測試訊息!"));
        System.out.println("群發成功");
        return "broad";
    }
 
}