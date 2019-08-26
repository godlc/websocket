package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();//请求头
    private WebSocketStompClient client=null;//stomp客户端
    private SockJsClient SockJsClient=null;//socket客户端
    private ThreadPoolTaskScheduler Ttask=null;//连接池
    private StompSession session=null;//连接会话
    private static Map<String, String> WebSocketConfig;//配置参数
    public  volatile boolean RecvFlag=false;

    @Test
    public void contextLoads() throws URISyntaxException {

//        System.out.println("ok");
//        List<Transport> transports = new ArrayList<>(2);
//        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        transports.add(new RestTemplateXhrTransport());
//
//        SockJsClient sockJsClient = new SockJsClient(transports);
//        sockJsClient.doHandshake(new MyHandler(), "ws://localhost:8083/portfolio");
//        System.out.println("ok");

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());

        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        //设置对应的解码器，理论支持任意的pojo自带转json格式发送，这里只使用字节方式发送和接收数据
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        stompClient.setReceiptTimeLimit(300);

        stompClient.setDefaultHeartbeat(new long[]{10000l,10000l});

        ThreadPoolTaskScheduler task=new ThreadPoolTaskScheduler();

        task.initialize();

        stompClient.setTaskScheduler(task);
        client=stompClient;
        SockJsClient=sockJsClient;
        Ttask=task;
        stompClient.connect("ws://localhost:8083/portfolio", new StompSessionHandlerAdapter(){
        }, "localhost", 8080);
    }
    }

