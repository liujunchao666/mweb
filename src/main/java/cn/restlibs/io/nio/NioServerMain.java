package cn.restlibs.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServerMain {

   /* ServerSocketChannel ssChannel;
    Selector selector;
    NioServerSub nioServerSub=new NioServerSub();
    public  void init() throws  Exception{
        //1. 获取服务端通道
        ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(9898));
        //2. 设置为非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 打开一个监听器
         selector = Selector.open();
        //4. 向监听器注册接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {      //阻塞
            //5. 获取监听器上所有的监听事件值
            Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //多个同事的事件
            //6. 如果有值
            while (it.hasNext()) {
                //7. 取到SelectionKey
                SelectionKey key = it.next();
                //8. 根据key值判断对应的事件
                if (key.isAcceptable()) {
                    //9. 接入处理
                    SocketChannel socketChannel = ssChannel.accept();
                //    nioServerSub.register(socketChannel);
                }
                //11. 移除当前key
                //1  得到连接socket 连接线程 2 注册到selector socket 事件线程 3 工作线程
                it.remove();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        NioServerMain nio=new NioServerMain();
        nio.init();

    }
*/
}
