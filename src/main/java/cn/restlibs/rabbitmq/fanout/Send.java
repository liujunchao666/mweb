package cn.restlibs.rabbitmq.fanout;


import cn.restlibs.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}

      /*  订阅模式
      先运行send。会创建交换机。但是没有队列。发送的消息丢失
      先运行客户端。没有交换机。绑定失败。报错
       1 先运行send 创建交换机 2再运行客户端 绑定。 或者再send里面创建交换机和绑定
       fanout交换机会把一个消息发送到所有的不同的队列中复制一份。相当于广播。
exchange的类型为：direct，topic，headers和fanout。

          之前我们通过空字符串（""）来标识exchange,就代表使用default (unnamed)  exchange。
      //使用了默认的交换器 Default exchange binding  QUEUE_NAME3 是 routingKey  direct
         channel.basicPublish("", QUEUE_NAME3, null, message.getBytes());

     defalut exchange会隐式的绑定到所有队列上每个队列上，routing key等于队列名，任何队列都不能够明确的指明绑定到default exchange,
     也不能从default exchange上解除绑定。default也不能够被删除


      在Java客户端中，当我们没有为queueDeclare（）提供参数时，意味着我们创建了一个具有随机名称的非持久，排他，自动删除的队列：
String queueName = channel.queueDeclare().getQueue();
queueName包含一个随机队列名称。 例如，它可能看起来像amq.gen-JzTY20BRgKO-HjmUJj0wLg。

      false,不删除队列   channel.queueDeclare(QUEUE_NAME3, false, false, false, null);

发布订阅 channel.queueBind(queueName, "logs", "");不需要routing key
发送时需要提供一个routingKey，但是对于faount类型的exchange来说，routing key的值是被忽略的，因为fanout是要广播所有从producer接受到的消息给所有绑定的队列


客户端一直不ack.一直不关闭。是不是这个消息一直不可用状态废了？


      * */