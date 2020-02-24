package web.data;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class WebSocketListener {

	public static JSONObject webSocketResponse  = new JSONObject();
	
	public static JSONObject getResponseFromWebSocket(String websocketURI, String input) {
		try {
			WebSocketClient mWs = new WebSocketClient(new URI(websocketURI),
					new Draft_6455())

			{
				@Override
				public void onMessage(String message) {
					webSocketResponse = new JSONObject(message);
					System.out.println("webSocketResponse: "+webSocketResponse);
				}

				@Override
				public void onOpen(ServerHandshake handshake) {
					System.out.println("opened connection");
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					System.out.println("closed connection");
				}

				@Override
				public void onError(Exception ex) {
					System.out.println("INside on ERROR");
					ex.printStackTrace();
				}

			};

			mWs.connect();
			Thread.sleep(2000);
			
			mWs.send(input);
			Thread.sleep(5000);

			mWs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return webSocketResponse;
		
	}
		public static void main(String a[]) {
			
			String input = "{\"action\":\"request-key\",\"apiVersion\":\"2.0\",\"device\":{\"deviceId\":\"test-device-id-sg_1551756433_16967\",\"deviceModel\":\"webClient\"}}";
			System.out.println("getValidTVCode: "+getResponseFromWebSocket("wss://api-prod.hooq.tv/1.1-alpha/device-pairing", input));

			JSONObject devicePairResponseData = (JSONObject) webSocketResponse.get("data");
			System.out.println("Data: "+devicePairResponseData);
			
			String tvCode = (String) devicePairResponseData.get("key");
			System.out.println("tvCode: "+tvCode);
		}
		
		

}