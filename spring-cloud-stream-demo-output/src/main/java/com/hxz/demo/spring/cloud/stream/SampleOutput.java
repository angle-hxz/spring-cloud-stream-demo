package com.hxz.demo.spring.cloud.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hxz
 * @since 2017/01/20 10:06
 */
@EnableBinding(Source.class)
public class SampleOutput {

    private static Long id = 0L;
    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "2000", maxMessagesPerPoll = "1"))
//    public MessageSource<String> timerMessageSource() {
//        return () -> {
//            System.out.println("Sending value: " + LocalDateTime.now());
//            return MessageBuilder.withPayload(LocalDateTime.now().toString()).setHeader(MessageHeaders.CONTENT_TYPE, "application/json").build();
//        };
//    }

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "1800", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerEntitySource() {
        return () -> {
            User user = new User();
            Long tempId = ++id;
            user.setId(tempId);
            user.setName("user" + tempId);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            System.out.println("Sending value: " + user.toString());
            try {
                return MessageBuilder.withPayload(objectMapper.writeValueAsString(user)).setHeader(MessageHeaders.CONTENT_TYPE, "application/json").build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}
