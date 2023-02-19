package com.test.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @ClassName:SpringQueueListener
 * @Auther: Aaronlizr
 * @Description: 队列监听
 * @Date: 2023/2/19 18:06
 * @Version: v1.0
 */
public class SpringQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
