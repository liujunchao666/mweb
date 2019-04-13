package cn.restlibs.rabbitmq.spring.shuxing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSendMain {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:rabbitmq-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean("amqpTemplate",RabbitTemplate.class);
        //发送消息
        template.convertAndSend("Hello, world!");
        ctx.destroy(); //容器销毁
    }
}

