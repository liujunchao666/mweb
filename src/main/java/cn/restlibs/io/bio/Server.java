package cn.restlibs.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try{
            ServerSocket serverSocket = new ServerSocket(888);
            Socket client = serverSocket.accept();

            BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter  writer=new PrintWriter(client.getOutputStream());

            System.out.println("客户端 ："+reader.readLine());

            writer.println("服务端");
            writer.flush();

            reader.close();
            writer.close();
            serverSocket.close();
            client.close();


        }catch (Exception e){

        }


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



