package com.test.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName:Producer_Topics
 * @Auther: Aaronlizr
 * @Description:
 * @Date: 2023/2/13 18:47
 * @Version: v1.0
 */
public class Producer_Topics {
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
        //5.创建交换机
        /*
         * exchange:交换机名称
         * type：交换机类型 DIRECT("direct")：定向,FANOUT("fanout")：扇形（广播），发送消息到每一个与之绑定的队列。
         *                TOPIC("topic")：通配符的方式,HEADERS("headers")：参数配置;
         * durable:是否持久化
         * autoDelete：自动删除
         * internal：内部使用。一般false
         * arguments：参数
         * */
        String exchangeName = "testTopics";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, null);
        //6.创建队列queue
        String queueName1 = "testTopicsQueue1";
        String queueName2 = "testTopicsQueue2";
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        //7.绑定队列和交换机
        /*
         * queue:队列的名称
         * exchange：交换机的名称
         * routingKey：路由键，绑定队列。如果交换机的类型为fanout，routingKey设置为""；
         * */
        // routing key 系统的名称，日志级别
        // 需求：所有的error级别的日志存入数据库，所有的order系统的日志存入数据库
        channel.queueBind(queueName1, exchangeName, "#.error");
        channel.queueBind(queueName1, exchangeName, "order.*");
        channel.queueBind(queueName2, exchangeName, "*.*");
        //8.消息的发送
        String body = "[ info ] test Is Topics Fanout";
        channel.basicPublish(exchangeName, "order.info", null, body.getBytes());
        channel.basicPublish(exchangeName, "holle.info.error", null, body.getBytes());
        channel.basicPublish(exchangeName, "info.error", null, body.getBytes());
        //释放资源
        channel.close();
        connection.close();
    }
}
