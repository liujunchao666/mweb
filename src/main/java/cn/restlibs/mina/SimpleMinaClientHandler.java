package cn.restlibs.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class SimpleMinaClientHandler extends IoHandlerAdapter {

	/**
	 * 当客户端接受到消息时
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		  String content = message.toString();
		  session.setAttribute("1", content);
	      System.out.println("" + content);

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
		session.write("Hello World!");
	}

}