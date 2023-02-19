package com.test.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName:Producer_WorkQueues
 * @Auther: Aaronlizr
 * @Description:
 * @Date: 2023/2/13 18:47
 * @Version: v1.0
 */
public class Producer_WorkQueues {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建链接工厂
        ConnectionFactory factory = new ConnectionFactory();
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
        //5.创建队列queue
        /*
         * queue:队列名称
         * durable:是否持久化，当mq重启之后，还在
         * exclusive:
         *       *是否独占：只能一个消费者监听这队列
         *       *当Connection关闭时，是否删除队列
         * autoDelete是否自动删除。当没有Consumer时，自动删除
         * arguments:参数
         * */
        //如果没有一个名字叫hello_world的队列，则会创建，如果存在则不会创建
        channel.queueDeclare("workQueues", true, false, false, null);
        //6.发送消息
        /*
         * exclusive:交换机的名称。简单模式下交换机会使用默认的""
         * routingKey:路由名称
         * props：配置信息
         * body：真实发送的消息数据
         * */
        for (int i = 1; i <= 10; i++) {
            String body = "is workQueues rabbitmq[" + i + "]......";
            channel.basicPublish("", "workQueues", null, body.getBytes());
        }
        //释放资源
        channel.close();
        connection.close();
    }
}
