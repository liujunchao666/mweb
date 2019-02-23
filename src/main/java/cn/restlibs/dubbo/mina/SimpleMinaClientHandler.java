package cn.restlibs.dubbo.mina;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.restlibs.dubbo.common.*;


public class SimpleMinaClientHandler extends IoHandlerAdapter {

	//这里应该是一个集合。不应该是一个对象。多线程并发了。是多个返回每个线程不同的对象。必须是集合。
	//map在多线程里。会不会有问题。
	Map resultmap ;
	/**
	 * 当客户端接受到消息时
	 */
	
	public SimpleMinaClientHandler( Map resultmap) {
		this.resultmap=resultmap;
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		//接受到Ojbect对象
		System.out.println("SimpleMinaClientHandler  messageReceived "+message);
		
		if(message instanceof Callback){
			
			Callback callback2=(Callback) message;
			String uuidString = callback2.getUuidString();
			Object object = resultmap.get(uuidString);
			Callback callobj=(Callback)object;
			
			
			synchronized (callobj) {
				System.out.println("callobj开始 "+callobj.hashCode());
				callobj.setResultObject(callback2.getResultObject());
				callobj.notifyAll();
				System.out.println("callobj结束 "+callobj.hashCode());
			}
		}
			 

	}
	
	@Override
	public void messageSent(IoSession session , Object message) throws Exception{
	//	System.out.println("client Sent 客户端发送消息：" + message);
    }

	/**
	 * 当一个客户端被关闭时
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("client disconnect");
	}

	/**
	 * 当一个客户端连接进入时
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {

		System.out.println("create connection to server :" + session.getRemoteAddress());
		
	}

}