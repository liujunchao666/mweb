package cn.restlibs.mina;

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
            // 绑定端口3456
            acceptor.bind(new InetSocketAddress(3456));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Mina server is listing port:3456" );

        return true;
    }
    
    public static void main(String[] args) {
        MinaServer server = new MinaServer();
        server.start();
    }
}


/*Mina server is listing port:3456
client connection : /127.0.0.1:59353
服务器接收到数据： Hello World!
服务器接收到数据： 连接服务器成功！
服务器接收到数据： 连接服务器成功！
服务器接收到数据： 我是张三。有人在吗
client connection : /127.0.0.1:59393
服务器接收到数据： 连接服务器成功！
服务器接收到数据： Hello World!
服务器接收到数据： 连接服务器成功！
服务器接收到数据： 我是李四。我刚进来。你好张三
服务器接收到数据： 李四你好。你今天吃饭了没
服务器接收到数据： 我每吃啊。好饿。咋办呢
服务器接收到数据： 李四。我有面包你吃不吃啊。
服务器接收到数据： 快给我吃。饿死了。呜呜。感谢你。你是好人
服务器接收到数据： 不客气了。咱两个关系好。拜拜
服务器接收到数据： 拜拜。张三
client disconnection : /127.0.0.1:59353 is Disconnection
client disconnection : /127.0.0.1:59393 is Disconnection
*/


/*Mina server is listing port:3456
client connection : /127.0.0.1:52084
/127.0.0.1:52084:连接服务器成功！
/127.0.0.1:52084:Hello World!
client connection : /127.0.0.1:52116
/127.0.0.1:52116:Hello World!
/127.0.0.1:52116:连接服务器成功！
/127.0.0.1:52084:我是客户1，有人吗
/127.0.0.1:52116:有人啊。我是客户2，你好啊
/127.0.0.1:52084:你好。我叫张三。你叫啥
/127.0.0.1:52116:我是客户2，李四
/127.0.0.1:52084:李四好。今天我请客。我张三有钱
/127.0.0.1:52116:好的。张老板
*/