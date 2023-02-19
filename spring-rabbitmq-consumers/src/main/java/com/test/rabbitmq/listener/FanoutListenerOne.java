package com.test.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @ClassName:FanoutListenerOne
 * @Auther: lizhr
 * @Description: 消息监听
 * @Date: 2023/2/19 18:10
 * @Version: v1.0
 */
public class FanoutListenerOne implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
