package com.quadcore.data.Config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.util.LinkedMultiValueMap;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class FilterChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        System.out.println("full message:" + message);
        LinkedMultiValueMap<String, Object> li =  (LinkedMultiValueMap)headerAccessor.getHeader("nativeHeaders");
        System.out.println("auth:" + li.get("Authorization"));
        System.out.println(headerAccessor.getHeader("nativeHeaders").getClass());
        //System.out.println("presend in serv: " + LocalDateTime.now());
        if (StompCommand.CONNECT.equals(headerAccessor.getCommand())) {
            System.out.println("msg: " + "conne");

        }
        //throw new MessagingException("no permission! ");
        return message;
    }
}