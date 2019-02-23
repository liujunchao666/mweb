package cn.restlibs.dubbo2.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import cn.restlibs.dubbo2.common.*;


public class MyServer {

		
	public static void main(String[] args) throws Exception {
		ServerSocket  server = null;  
		Socket client=null;
		//server = new ServerSocket(8090, 3, InetAddress.getByName("127.0.0.1"));  
    

		try {
			// server = new ServerSocket(8090, 1, InetAddress.getByName("127.0.0.1"));  
			 server=new ServerSocket(8090, 1);  // 1是队列中最大的连接数
			 int i=1;
		//	 Thread.sleep(10*1000);
			while (true) {
				
				client = server.accept();  //accept()从队列中取出连接数
				System.out.println("第"+i);
				
				final InputStream inputStream = client.getInputStream();
			 	final OutputStream outputStream=client.getOutputStream();

			    Hessian2Input h2i = new Hessian2Input(inputStream);  
			   
		        h2i.startMessage();  
		        
		    
		    	Callback callback = (Callback) h2i.readObject();  
		        System.out.println("receive:" + callback.toString());  
		        System.out.println("receive:" + callback.getInterfaceName()+" "+ callback.getMethod());  

		        System.out.println(h2i.readString());  
		        h2i.completeMessage();  
		        h2i.close();  
			        
		        
		        String interfaceName = callback.getInterfaceName();
		        Object[] args2 = callback.getArgs();
			//	Method method = callback.getMethod();
		        String methodAll=callback.getMethod();
		        
		        
		       // public abstract com.dubbo.common.Person com.dubbo.common.ManagerService.eat(com.dubbo.common.Apple)
		        String[] split = methodAll.split(" ");
		        String returntype = split[2];
		        String methodnames = split[3];
		        String[] split2 = methodnames.split("\\(");
		        String method1 = split2[0];
		        int lastIndexOf = method1.lastIndexOf(".");
		        String methodname = method1.substring(lastIndexOf+1, method1.length());
		        String artypes = split2[1];
		        String arytype = artypes.substring(0, artypes.length()-1);
		        interfaceName=interfaceName+"Impl";
		        
				Object o = Class.forName(interfaceName).newInstance();
		
				Method method = o.getClass().getMethod(methodname,	Class.forName(arytype)); //传入的是实现类的接口，必须是一个接口。
				
				Object result = method.invoke(o, args2);
				
			    PrintStream writer = new PrintStream(outputStream);
		      
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				Hessian2Output h2o = new Hessian2Output(os);
				h2o.startMessage();
				h2o.writeObject(result);
				h2o.writeString("I am server.");
				h2o.completeMessage();
				h2o.close();
 
				byte[] buffer = os.toByteArray();
				os.close();

		        writer.write(buffer);
			     writer.flush();
				 writer.close();

		           
		        i++;
			}
			
		} catch (Exception e) {
	        
			e.printStackTrace();
		}
		
	}
	


}
