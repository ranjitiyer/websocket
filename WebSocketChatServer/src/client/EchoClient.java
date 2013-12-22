package client;


import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.Future;

/**
* Example of a simple Echo Client.
*/
public class EchoClient {

   public static void main(String[] args) {
       String destUri = "ws://localhost:8001/chat";
       if (args.length > 0) {
           destUri = args[0];
       }
       WebSocketClient client = new WebSocketClient();
       ClientEchoSocketListener socket = new ClientEchoSocketListener();
       try {
           client.start();
           URI echoUri = new URI(destUri);
           ClientUpgradeRequest request = new ClientUpgradeRequest();
           Future<Session> future = client.connect(socket, echoUri, request);
           Session session = future.get();
           System.in.read();
       } catch (Throwable t) {
           t.printStackTrace();
       } finally {
           try {
               client.stop();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
}