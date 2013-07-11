package com.hascode.tutorial;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

	@OnMessage
	public void onMessage(final Session session, final String message) {
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					s.getBasicRemote().sendText(
							"Message received at " + new Date().toString()
									+ ": " + message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
