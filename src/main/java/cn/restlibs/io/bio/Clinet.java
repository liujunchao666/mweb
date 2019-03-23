package cn.restlibs.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Clinet {


    public static void main(String[] args) {

        try{

            for(int i=0;i<100000;i++){
                System.out.println("qwe " +i+" ");

                try{


                    Socket   socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 80),3000);

            PrintWriter  writer=new PrintWriter(socket.getOutputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                }catch (Exception e){
                    System.out.println("qwe " +i+" "+e.getMessage());
                }
       /*     writer.println("客户端");
            writer.flush();

            System.out.println("客户端 ："+reader.readLine());

            reader.close();
            writer.close();
            socket.close();*/
            }

        }catch (Exception e){

            System.out.println("12345");
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



