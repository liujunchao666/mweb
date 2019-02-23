package cn.restlibs.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {


    public static void main(String[] args) {


    /*    Object obj = Proxy.newProxyInstance(TestProxy.class.getClassLoader(), new Class[]{Start.class}, new InvocationHandler() {

            Gmm gmm=new Gmm();       //被代理对象
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("log 开始");
                Object obj = method.invoke(gmm, args);
                System.out.println("log 结束");
                return obj;
            }
        });

        Start s=(Start)obj;
        s.sing();
        s.dance();
        s.say("hehe");*/


        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Gmm.class.getInterfaces());
        String path = "d:/javacode/Start.class";
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("写文件错误");
        }


    }
}
