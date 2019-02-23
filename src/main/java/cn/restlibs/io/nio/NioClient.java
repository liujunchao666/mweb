package cn.restlibs.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
public class NioClient {

    public static void main(String[] args)  throws Exception {

        //1. 获取socketChannel
        SocketChannel sChannel = SocketChannel.open();
        //2. 创建连接
        sChannel.connect(new InetSocketAddress("127.0.0.1", 9898));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3. 设置通道为非阻塞
        sChannel.configureBlocking(false);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {       //阻塞
            String msg = scanner.nextLine();
            buf.put((new Date() + "：" + msg).getBytes());
            buf.flip();
            //4. 向通道写数据
            sChannel.write(buf);
            buf.clear();
        }
    }


}