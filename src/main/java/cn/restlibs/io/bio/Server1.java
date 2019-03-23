package cn.restlibs.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    public static void main(String[] args) {

        ServerSocket serverSocket =null;
        Socket client = null;
        try {
             serverSocket = new ServerSocket(888);


               client = serverSocket.accept();  //阻塞
                System.out.println("server socket is start……");


                    OutputStream out = client.getOutputStream();
                    InputStream input = client.getInputStream();

                    byte[] buf = new byte[128];
                    int size = 0;
                    while ((size = input.read(buf)) != -1) {   //1没有数据阻塞 2不关闭阻塞  //   client.shutdownOutput();  //注释后双方都等待输出 打开表示关闭 服务器收到 -1
                        System.out.print(new String(buf,0,buf.length));
                    }
                    out.write("  服务端 sender say hello socket to 客户端 ".getBytes());
                    out.flush();

                     out.close(); //不关闭对面能收到。但是一直等待。
                     input.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



