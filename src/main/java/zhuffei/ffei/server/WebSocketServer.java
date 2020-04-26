package zhuffei.ffei.server;

import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;


@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

    private static ConcurrentHashMap<String, Session> webSocktClients = new ConcurrentHashMap<String, Session>();

    /**
     * 连接建立后触发的方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        webSocktClients.put(userId, session);
        System.out.println("连接建立，userId="+userId);
//        sendMessageToUsers("{\"lat\":32.038231,\"lng\":118.643132,\"uid\":3}","2");
    }

    /**
     * 连接关闭后触发的方法
     * @param userId
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        webSocktClients.remove(userId);
        System.out.println("连接关闭，userId="+userId);
    }

    /**
     * 接收到客户端消息时触发的方法
     * @param message 格式 [消息接收人id,消息接收人id....:消息内容],消息接收人和消息内容使用:分隔，接收人在前，内容在后
     * @param userId 消息发送人id
     */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId) {
        System.out.println("服务器接收到userId="+userId+"发来的消息，"+message);
//        int index = message.indexOf(":");
//		if (index != -1) {
//			sendMessageToUsers(message.substring(index+1), message.substring(0,index).split(","));
//		} else {
//            System.out.println("群发");
////			System.out.println("服务端转发【" + userId + "】的群消息.");
//			sendMessage(message);
//		}

        Map map = JSON.parseObject(message, HashMap.class);
        BigDecimal lat = (BigDecimal) map.get("lat");
        BigDecimal lng = (BigDecimal) map.get("lng");
        String accid = (String) map.get("target");
        map.remove("uid");
        String msg = JSON.toJSONString(map);
        sendMessageToUsers(msg,accid);
    }

    /**
     * 发生错误时触发的方法
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    /**
     * 发送系统消息
     * @param msg
     * @return
     */
    public static boolean sendMessage(String msg) {
        boolean flag = false;
        try {
            for (Session session : webSocktClients.values()) {
                session.getBasicRemote().sendText(msg);
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 发送个人消息或群组消息
     * @param msg
     * @param userids
     * @return
     */
    public static boolean sendMessageToUsers(String msg, String... userids) {

        boolean flag = false;
        try {
            if (userids != null && userids.length > 0) {
                for (String userid : userids) {
                    System.out.println("给"+userid+"发消息，内容"+msg);
                    Session session = webSocktClients.get(userid);
                    if(session!=null){
                        session.getBasicRemote().sendText(msg);
                    }else{
                        System.out.println("找不到userid="+userid+"的会话");
                    }
                }
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
