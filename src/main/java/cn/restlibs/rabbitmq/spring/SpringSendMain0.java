package cn.restlibs.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSendMain0 {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:rabbitmq-context.xml");
        //RabbitMQ模板
       RabbitTemplate template = ctx.getBean("amqpdirectTemplate",RabbitTemplate.class);
        //发送消息
        template.convertAndSend("myDirectQueueKey","Hello, world!");
        Thread.sleep(1000);// 休眠1秒
        ctx.destroy(); //容器销毁
    }
}
