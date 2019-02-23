package cn.restlibs.dubbo.client;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;


import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import cn.restlibs.dubbo.common.*;
import cn.restlibs.dubbo.invoker.MyInvoker;
import org.junit.Test;

public class Projectweb1 {


	
	@Test
	public  void test1() throws UnknownHostException, IOException, InterruptedException {
		
		//开2个线程同时调用方法。
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				MyInvoker invoker=new MyInvoker();
                Object obj = invoker.getManagerService();
				ManagerService   managerService=(ManagerService)obj;   
				//每次调用会不生成新的ManagerService
				
				Apple apple=new Apple();
				apple.setName("红富士");
				apple.setWeight(222.0);

				
				Person say = managerService.eat(apple);	
				System.out.println("receive:" + say.toString());  
				System.out.println("receive:" + say.getBrithday()+" "+ say.getName());  
				
			}
		});
		
		thread.start();
                
				
	Thread thread2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				MyInvoker invoker=new MyInvoker();
                Object obj = invoker.getManagerService();
				ManagerService   managerService=(ManagerService)obj;   
				//每次调用会不生成新的ManagerService
				
				Apple apple=new Apple();
				apple.setName("青苹果");
				apple.setWeight(222.0);

				
				Person say = managerService.eat(apple);	
				System.out.println("receive:" + say.toString());  
				System.out.println("receive:" + say.getBrithday()+" "+ say.getName());  
				
			}
		});
		
	thread2.start();		
				
	
	Thread.sleep(100000);
				
	}
			
	
	 
	
				
			

}
