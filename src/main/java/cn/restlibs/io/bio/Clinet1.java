package cn.restlibs.io.bio;

import java.io.*;
import java.net.Socket;

public class Clinet1 {


    public static void main(String[] args) {

        try{

            Socket client = new  Socket("127.0.0.1",888);
            OutputStream out = client.getOutputStream();
            InputStream input = client.getInputStream();

            out.write("客户端 sender say hello socket to 服务端".getBytes()); //不写对方会阻塞
            out.flush();
            client.shutdownOutput();  //注释后双方都等待输出 打开表示关闭 服务器收到 -1

            byte[] buf = new byte[128];
            int size = 0;
            while ((size = input.read(buf)) != -1) {  //没有数据阻塞  不关闭阻塞      out.close(); //不关闭对面能收到。但是一直等待。
                System.out.print(new String(buf,0,buf.length));
            }

            out.close();
            input.close();

        }catch (Exception e){

        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}



