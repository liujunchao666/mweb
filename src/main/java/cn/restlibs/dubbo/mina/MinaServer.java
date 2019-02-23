package cn.restlibs.dubbo.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

 
/**
 * <b>function:</b> 服务器启动类
 */
public class MinaServer {
    
    private SocketAcceptor acceptor;
    
    public MinaServer() {
        // 创建非阻塞的server端的Socket连接
        acceptor = new NioSocketAcceptor();
    }
    
    public boolean start() {
        DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
        // 添加编码过滤器 处理乱码、编码问题
        filterChain.addLast("codec", new ProtocolCodecFilter(new CharsetCodecFactory()));
        
        /*LoggingFilter loggingFilter = new LoggingFilter();
        loggingFilter.setMessageReceivedLogLevel(LogLevel.INFO);
        loggingFilter.setMessageSentLogLevel(LogLevel.INFO);
        // 添加日志过滤器
        filterChain.addLast("loger", loggingFilter);*/
        
        // 设置核心消息业务处理器
        acceptor.setHandler(new SimpleMinaServerHandler());
        // 设置session配置，30秒内无操作进入空闲状态
     //   acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
        
        try {
          
            acceptor.bind(new InetSocketAddress(8090));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Mina server is listing port:8090" );

        return true;
    }
    
    public static void main(String[] args) {
        MinaServer server = new MinaServer();
        server.start();
    }
}
