package server;

import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.UpgradeResponse;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class ChatServer {
	public static void main(String[] args) throws Exception {
		Server s = new Server(8001);
		s.setHandler(new WebSocketHandler() {
			@Override
			public void configure(WebSocketServletFactory factory) {
				factory.setCreator(new WebSocketCreator() {
					@Override
					public Object createWebSocket(UpgradeRequest arg0, UpgradeResponse arg1) {
						return (Object) new EchoWebSocket();
					}
					
				});
			}
		});
		s.start();
		s.join();
		System.out.println("DONE");
	}
	
	static class EchoWebSocket implements WebSocketListener {
		private RemoteEndpoint other;
		
		EchoWebSocket () {
			
		}
		
		@Override
		public void onWebSocketBinary(byte[] arg0, int arg1, int arg2) {
		}

		@Override
		public void onWebSocketClose(int arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onWebSocketConnect(Session session) {
			try {
				other = session.getRemote();
				other.sendString("CONNECTED");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void onWebSocketError(Throwable arg0) {
			
		}

		@Override
		public void onWebSocketText(String msg) {
			try {
				System.out.println(msg);			
				//while (true) {
				other.sendString("PONG");
				Thread.sleep(500);
				//}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
