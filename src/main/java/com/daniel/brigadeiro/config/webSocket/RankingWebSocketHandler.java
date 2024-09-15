package com.daniel.brigadeiro.config.webSocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class RankingWebSocketHandler extends TextWebSocketHandler{

	private static final List<WebSocketSession> sessions = new ArrayList<>();
	
	 @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	        sessions.add(session);
	        System.out.println("Sessão estabelecida: " + session.getId());
	    }
	 
	 @Override
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	        sessions.remove(session);
	        System.out.println("Sessão fechada: " + session.getId());
	    }

	    public void sendMessageToClients(String message) throws Exception {
	    	System.out.println("Número de sessões ativas: " + sessions.size());
	        for (WebSocketSession session : sessions) {
	            if (session.isOpen()) {
	                session.sendMessage(new TextMessage(message));
	            }
	        }
	    }
}
