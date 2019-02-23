package cn.restlibs.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServerSub {

   /* Selector selector;
    public  void register(SocketChannel socketChannel) throws  Exception{

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {      //阻塞
            //5. 获取监听器上所有的监听事件值
            Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //多个同事的事件
            //6. 如果有值
            while (it.hasNext()) {
                //7. 取到SelectionKey
                SelectionKey key = it.next();
                //8. 根据key值判断对应的事件
              if (key.isReadable()) {
                    //10. 可读事件处理

                    //开启线程  非io操作？ 解码 处理 编码
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                SocketChannel channel = (SocketChannel) key.channel(); //  非io操作？ 解码 处理 编码
                                ByteBuffer buf = ByteBuffer.allocate(1024);
                                int len = 0;
                                while ((len = channel.read(buf)) > 0) {
                                    buf.flip();
                                    byte[] bytes = new byte[1024];
                                    buf.get(bytes, 0, len);
                                    System.out.println(new String(bytes, 0, len));
                                }

                            }catch (Exception e){

                            }


                        }
                    }).start();


                }
                //11. 移除当前key
                //1  得到连接socket 连接线程 2 注册到selector socket 事件线程 3 工作线程
                it.remove();
            }
        }

    }*/


}
