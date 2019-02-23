package cn.restlibs.dubbo2.client;

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
import cn.restlibs.dubbo2.common.*;
public class MyClient {


	
	public static void main(String[] args) throws UnknownHostException, IOException {






		cn.restlibs.dubbo2.common.Apple apple=new cn.restlibs.dubbo2.common.Apple();
		apple.setName("红富士");
		apple.setWeight(222.0);
	 	
	   Object obj = Proxy.newProxyInstance(MyClient.class.getClassLoader(), new Class[]{ManagerService.class}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				
					final Socket socket1=new Socket();
					socket1.setSoTimeout(2*1000);
					socket1.setKeepAlive(true);
					SocketAddress sa=new InetSocketAddress("127.0.0.1", 8090);
					socket1.connect(sa, 1000*2); //握手成功
				//在3队列个队列里，就是连接上了  true
					final InputStream inputStream = socket1.getInputStream();
				 	final OutputStream outputStream=socket1.getOutputStream();
			 	
				
					Callback callback=new Callback();
					
					callback.setMethod(method.toString());
					callback.setArgs(args);
					callback.setInterfaceName(ManagerService.class.getName());
		
			        PrintStream writer = new PrintStream(outputStream);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					Hessian2Output h2o = new Hessian2Output(os);
					h2o.startMessage();
					h2o.writeObject(callback);
					h2o.writeString("I am client.");
					h2o.completeMessage();
					h2o.close();
	 
					byte[] buffer = os.toByteArray();
					os.close();

			        writer.write(buffer);
				    writer.flush();
				    socket1.shutdownOutput();

				    
				    Hessian2Input h2i = new Hessian2Input(inputStream);  
					   
			        h2i.startMessage();  
			        Person person = (Person) h2i.readObject();  
		
			        h2i.completeMessage();  
			        h2i.close();  
			        
			        inputStream.close();
			        
				
				return person;
			}
		});
		
	   
	    ManagerService   managerService=(ManagerService)obj;   
	   
	   
	     Person say = managerService.eat(apple);
		
	
   
        System.out.println("receive:" + say.toString());  
        System.out.println("receive:" + say.getBrithday()+" "+ say.getName());  

	       
	    
	


		

	}
}
