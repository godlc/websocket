package com.example.demo.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author 城哥
 */
@Controller
public class GreetingController {

    @MessageMapping("/greeting")
    public String handle(String greeting) {
        return "[" + new Date().toString() + ": " + greeting;
    }
}
