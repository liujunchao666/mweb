package cn.restlibs.rabbitmq.routing;


import cn.restlibs.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "key", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


  /*  一个queue绑定到多个exchange上
    最近学习rabbitmq，想到个问题：
    一个exchange绑定1000个queue，和5个exchange，每个exchange绑定200个queue，这两种设计，在消息转发的效率上，会有差别吗

    default 交换机 队列怎么解绑

*/

}