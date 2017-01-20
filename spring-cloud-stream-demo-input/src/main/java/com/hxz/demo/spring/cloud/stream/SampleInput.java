package com.hxz.demo.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author hxz
 * @since 2017/01/20 10:23
 */
@EnableBinding(Sink.class)
public class SampleInput {
//    @StreamListener(Sink.INPUT)
//    public void receiveMsg(String message) {
//        System.out.println("Received message " + message);
//    }

    @StreamListener(Sink.INPUT)
    public void receiveEntity(User user) {
        System.out.println("Received message " + user.toString());
    }
}
