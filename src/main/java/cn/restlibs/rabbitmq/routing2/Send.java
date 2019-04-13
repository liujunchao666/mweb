package cn.restlibs.rabbitmq.routing2;


import cn.restlibs.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct_test";
    private final static String QUEUE_NAME2= "test_queue_direct_test2";
    private final static String QUEUE_NAME1 = "test_queue_direct_test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 声明队列
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        // 声明队列
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);

        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "key");
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "key4");

        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "key2");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "key3");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "key");
        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "key", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }



}