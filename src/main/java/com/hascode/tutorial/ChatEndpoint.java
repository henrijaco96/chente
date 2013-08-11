package com.hascode.tutorial;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{userName}/{room}")
public class ChatEndpoint {

	@OnMessage
	public void onMessage(final Session session, final String message,
			@PathParam("userName") final String userName,
			@PathParam("room") final String room) {
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					s.getBasicRemote().sendText(
							String.format("(%s) %s@%s: %s",
									new Date().toString(), userName, room,
									message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
