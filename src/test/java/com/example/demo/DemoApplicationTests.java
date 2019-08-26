package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Test
    public void contextLoads() throws URISyntaxException, ExecutionException, InterruptedException {

//        System.out.println("ok");
//        List<Transport> transports = new ArrayList<>(2);
//        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        transports.add(new RestTemplateXhrTransport());
//
//        SockJsClient sockJsClient = new SockJsClient(transports);
//        sockJsClient.doHandshake(new MyHandler(), "ws://localhost:8083/portfolio");
//        System.out.println("ok");
//        WebSocketClient webSocketClient = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//        stompClient.setMessageConverter(new StringMessageConverter());
////        stompClient.setTaskScheduler(taskScheduler);
//
        String url = "ws://127.0.0.1:8083/portfolio";
//        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter(){};
//        ListenableFuture<StompSession> f =  stompClient.connect(url, sessionHandler);
//        StompSession session = f.get();
//        System.out.println(11);

        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
        WebSocketClient transport = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(transport);
        ListenableFuture<StompSession> f = stompClient.connect(url,new StompSessionHandlerAdapter(){});
        StompSession session = f.get();
        System.out.println(1);
    }
}

