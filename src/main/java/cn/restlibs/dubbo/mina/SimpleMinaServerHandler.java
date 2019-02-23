package cn.restlibs.dubbo.mina;


import org.apache.mina.core.service.IoHandlerAdapter;  
import org.apache.mina.core.session.IoSession;  
  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.restlibs.dubbo.*;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import cn.restlibs.dubbo.common.*;


public class SimpleMinaServerHandler extends IoHandlerAdapter {  

   
  
    /** 
     * 当一个客户端连接进入时 
     */  
    @Override  
    public void sessionOpened(IoSession session) throws Exception {  
  
        System.out.println("client connection : " + session.getRemoteAddress());  
  
    }  
  
    /** 
     * 当一个客户端关闭时 
     */  
    @Override  
    public void sessionClosed(IoSession session) throws Exception {  
  
        System.out.println("client disconnection : " +session.getRemoteAddress() + " is Disconnection");  
  
    }  
  
    /** 
     * 当接收到客户端的信息 
     * 
     * @param session 
     * @param message 
     * @throws Exception 
     */  
    @Override  
    public void messageReceived(IoSession session, Object message)  
            throws Exception {  
    	
    	//接受到Ojbect对象
		   System.out.println("SimpleMinaServerHandler  messageReceived "+message);

	       	Callback	callback=(Callback) message;
	        
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

			callback.setResultObject(result);			
	        session.write(callback);
	        
	        System.out.println("SimpleMinaServerHandler  messageReceived send "+callback);
			
         }
  

  
}  