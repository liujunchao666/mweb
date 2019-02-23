package cn.restlibs.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Clinet {


    public static void main(String[] args) {

        try{

            Socket socket = new  Socket("127.0.0.1",888);

            PrintWriter  writer=new PrintWriter(socket.getOutputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("客户端");
            writer.flush();

            System.out.println("客户端 ："+reader.readLine());

            reader.close();
            writer.close();
            socket.close();


        }catch (Exception e){

        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



