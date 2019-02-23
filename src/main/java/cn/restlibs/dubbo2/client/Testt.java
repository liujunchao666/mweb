package cn.restlibs.dubbo2.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import cn.restlibs.dubbo2.common.*;

public class Testt {

	
	
	public static void main(String[] args) {
		
		 Apple apple=new Apple();
		apple.setName("红富士");
		apple.setWeight(222.0);
		final ManagerService managerService=new ManagerServiceImpl();
		
	   Object obj = Proxy.newProxyInstance(MyClient.class.getClassLoader(), new Class[]{ManagerService.class}, new InvocationHandler() {
		
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				System.out.println("1");
				Object obj = method.invoke(managerService, args);  
				System.out.println("2");

				return obj;
			}
		});
		
	   ManagerService managerService2=(ManagerService)obj;   
	   Person eat = managerService2.eat(apple);
       System.out.println("receive:" + eat.getBrithday()+" "+ eat.getName());  

	}
}
