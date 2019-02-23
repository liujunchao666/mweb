package cn.restlibs.dubbo.mina;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;



 
/**
 * <b>function:</b> mina客户端
 */
public class MinaClient {
 
    private SocketConnector connector;
    private ConnectFuture future;
    private IoSession session;
    Map<String,Object> resultmap;

    
    public boolean connect() {
 
        // 创建一个socket连接
        connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(3000);
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        // 添加编码过滤器 处理乱码、编码问题
        filterChain.addLast("codec", new ProtocolCodecFilter(new CharsetCodecFactory()));
 
        /*
        // 日志
        LoggingFilter loggingFilter = new LoggingFilter();
        loggingFilter.setMessageReceivedLogLevel(LogLevel.INFO);
        loggingFilter.setMessageSentLogLevel(LogLevel.INFO);
        filterChain.addLast("loger", loggingFilter);*/
 
        // 消息核心处理器
        resultmap=new HashMap<String, Object>();
        connector.setHandler(new SimpleMinaClientHandler(resultmap));
 
        // 连接服务器，知道端口、地址
       
        future =  connector.connect(new InetSocketAddress("127.0.0.1",8090));
        // 等待连接创建完成
        future.awaitUninterruptibly();
        // 获取当前session
        session = future.getSession();
        
    
        return true;
    }
 
    public void setAttribute(Object key, Object value) {
        session.setAttribute(key, value);
    }
 
    public void send(Object message) {
        session.write(message);
    }
 
    public boolean close() {
        CloseFuture future = session.getCloseFuture();
        future.awaitUninterruptibly(1000);
        connector.dispose();
        return true;
    }
 
    public SocketConnector getConnector() {
        return connector;
    }
 
    public IoSession getSession() {
        return session;
    }

	public Map getResultmap() {
		return resultmap;
	}

	public void setResultmap(Map resultmap) {
		this.resultmap = resultmap;
	}


    
    
}