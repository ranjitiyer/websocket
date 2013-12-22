package client;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import java.io.IOException;

public class ClientEchoSocketListener implements WebSocketListener {
	private Session session = null;

	@Override
	public void onWebSocketBinary(byte[] arg0, int arg1, int arg2) {
		System.out.println("Got binary");
	}

	@Override
	public void onWebSocketClose(int arg0, String arg1) {
		System.out.println("Closed");
	}

	@Override
	public void onWebSocketConnect(Session arg0) {
		System.out.println("Connected");		
		session = arg0;
		try {
			session.getRemote().sendString("PING");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onWebSocketError(Throwable arg0) {
		System.out.println("Error ");		
	}

	@Override
	public void onWebSocketText(String arg0) {
		try {
			System.out.println(arg0);
			session.getRemote().sendString("Ping");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
