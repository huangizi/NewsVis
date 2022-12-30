package com.freya.onlinetech.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{username}")  //前端通过此URI 和后端交互，建立连接
public class WebSocket {
//websocket的具体实现类。创建和管理到服务器的WebSocket连接，以及在连接上发送和接收数据。相当于一个ws协议的Controller

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    //ConcurrentHashMap线程安全；避免了多个线程竞争同一把锁的情况，大大提高了性能。
    // 用于接收当前user的WebSocket
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    /**
     * OnOpen 表示有浏览器链接过来的时候被调用
     * OnClose 表示浏览器发出关闭请求的时候被调用
     * OnMessage 表示浏览器发消息的时候被调用
     * OnError 表示有错误发生，比如网络断开了等等
     */

    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        log.info("现在连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
//        System.out.println("session: "+session);
        log.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 1);
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);

            //把自己的信息加入到map当中去
            clients.put(username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 = new HashMap<>();
            map2.put("messageType", 3);

            Set<String> set = clients.keySet();//Set:用于存储无序(存入和取出的顺序不一定相同)元素，值不能重复。  返回了所有用户名的集合
            map2.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (IOException e) {
            log.info(username + "：上线的时候发生错误");
        }


    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
        //error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        //webSockets.remove(this);
        clients.remove(username);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 2);
            map1.put("onlineUsers", clients.keySet());//在线名单
            map1.put("username", username);//下线用户名
            sendMessageAll(JSON.toJSONString(map1), username);
        } catch (IOException e) {
            log.info(username + "：下线的时候发生错误");
        }
        log.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            //先解析发送过来的是文字还是图片   1文字，2图片
            JSONObject jsonObject = JSON.parseObject(message);
//            System.out.println("jsonObject :"+jsonObject);
            String type = jsonObject.getString("type");
            System.out.println("type: "+type);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            Map<String, Object> map1 = new HashMap<>();
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromusername);

            if(type.equals("1")){
                log.info("来自客户端文字消息：" + message + "客户端的id是：" + session.getId());

                //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息 5图片
                map1.put("messageType", 4);

            }
            else{
//                log.info("来自客户端图片的地址：" + message + "客户端的id是：" + session.getId());
                log.info("收到来自客户端的图片。" + "客户端的id是：" + session.getId());

                //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息 5图片
                map1.put("messageType", 5);
            }

            //如果不是发给所有，那么就发给某一个人
            if (tousername.equals("All")) {
                map1.put("tousername", "所有人");
                sendMessageAll(JSON.toJSONString(map1), fromusername);
            } else {
                map1.put("tousername", tousername);
                sendMessageTo(JSON.toJSONString(map1), tousername);
            }
        } catch (Exception e) {
            log.info("发生了错误");
            e.printStackTrace();
        }
    }

    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (WebSocket item : clients.values()) {//遍历collection
            //群发消息不给自己发
            if (!item.username.equals(FromUserName)) {
                item.session.getAsyncRemote().sendText(message);// getAsyncRemote()和getBasicRemote() 是异步与同步的区别;后者发送多行消息会抛出IllegalStateException异常
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}
