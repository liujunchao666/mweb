package cn.restlibs.tomcat;

import java.net.InetSocketAddress;
import java.net.Socket;

public class TestTomcat {

    public static void main(String[] args) {


        for (int i = 1; i <= 300; i++) {
            System.out.println("qwe " + i + " ");
            try {
                Socket socket = null;
                socket = new Socket();
                socket.connect(new InetSocketAddress("localhost", 80), 10000);        //代表握手成功

            } catch (Exception e) {
                System.out.println("qwe " + i + " " + e.getMessage());
            }
        }
    }
}
