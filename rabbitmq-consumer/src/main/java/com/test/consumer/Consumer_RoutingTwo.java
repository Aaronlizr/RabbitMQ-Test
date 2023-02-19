package com.test.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName:Consumer_RoutingTwo
 * @Auther: Aaronlizr
 * @Description:
 * @Date: 2023/2/15 11:10
 * @Version: v1.0
 */
public class Consumer_RoutingTwo {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置参数
        //2.设置参数
        factory.setHost("localhost");//ip地址，默认值为localhost
        factory.setPort(5672);//端口 默认值为5672
        factory.setVirtualHost("/test");//虚拟机
        factory.setUsername("testuser");
        factory.setPassword("testpass");
        //3.创建链接 connection
        Connection connection = factory.newConnection();
        //4.创建channel
        Channel channel = connection.createChannel();

        String queueName2 = "testDirectQueue2";

        //接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            /*
             * 回调方法，当收到消息后会自动执行改方法
             * consumerTag:标识
             * envelope：获取一些信息：交换机，路由key
             * properties：配置信息
             * body：数据
             * */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("body:" + new String(body));
            }
        };
        channel.basicConsume(queueName2, true, consumer);
        //消费者相当于监听程序，不需要关闭资源
    }
}
