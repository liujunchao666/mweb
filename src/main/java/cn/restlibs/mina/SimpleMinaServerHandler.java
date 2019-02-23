package cn.restlibs.mina;


import org.apache.mina.core.service.IoHandlerAdapter;  
import org.apache.mina.core.session.IoSession;


import java.util.Collection;


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
  
    	System.out.println(session.getRemoteAddress()+":"+ message);
    	 
         String content = message.toString();
      
         
         // 拿到所有的客户端Session
         Collection<IoSession> sessions = session.getService().getManagedSessions().values();
         // 向所有客户端发送数据
         for (IoSession sess : sessions) {
        	 if (sess!=session) {
        		 sess.write(sess.getRemoteAddress() + ":" + content);
			}
             
         }
  
    }  
  
}  