package cn.restlibs.dubbo.invoker;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import cn.restlibs.dubbo.client.Projectweb1;
import cn.restlibs.dubbo.common.Callback;
import cn.restlibs.dubbo.common.ManagerService;
import cn.restlibs.dubbo.common.Person;
import cn.restlibs.dubbo.mina.SimpleMinaClientHandler;
import cn.restlibs.dubbo.mina.MinaClient;

public class MyInvoker {
	
	
	//使用静态块静态方法。只开一个客户端端口。
	//使用new 客户端可以开多个端口。
	
	//new 一次开一个客户端端口。
	 MinaClient client ; 
     Map<String,Object> resultmap;
	
	public  MyInvoker() { //new 一次开一个客户端端口。
	    client = new MinaClient();
        client.connect();
        resultmap=client.getResultmap();
	}
	
	//static 客户端开一个端口
/*	static MinaClient client ;  //一个项目对应一个示例。一个项目对应一个端口。长连接。
    static Map<String,Object> resultmap;//一个项目对应一个集合。取完清空。
	
	static{ 
	    client = new MinaClient();
        client.connect();
        resultmap=client.getResultmap();
	}
*/
	
	//
	public   Object   getManagerService(){
		

			 Object obj = Proxy.newProxyInstance(Projectweb1.class.getClassLoader(), new Class[]{ManagerService.class}, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
				
						Callback callback=new Callback();
						String uuid=UUID.randomUUID().toString();
						callback.setUuidString(uuid);
						callback.setMethod(method.toString());
						callback.setArgs(args);
						callback.setInterfaceName(ManagerService.class.getName());

						resultmap.put(uuid, callback);
						client.send(callback);
					
						Object callobj =null;
						Object resultObject =null;
						 
						 //callback是每次调用生成新的对象。每次调用都生成不同的对象。在不同的对象加锁。对每次调用不影响。
					
						synchronized (callback) {
							System.out.println("callback 开始 "+callback.hashCode());

							 callobj = resultmap.get(uuid);
						     Callback callback2= (Callback) callobj;
							  resultObject = callback2.getResultObject();
							 
								 //这里必须返回对象可能是空的。如果为空。必须等待服务器返回结果。
							  //接受到结果后。唤醒这个线程。从新获取结果。
							  //sleep也可以。但是不好。时间不确定。
						    if (resultObject==null) {
						    	callback.wait();
							}
							    
							  resultObject = callback2.getResultObject();
						      System.out.println("callback 结束 "+callback.hashCode());

						}
	
					return resultObject;
				}
			});
			
			
		return obj;
	}


	
	
	
}
