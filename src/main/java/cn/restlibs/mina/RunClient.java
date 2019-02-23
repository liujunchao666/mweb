package cn.restlibs.mina;


import java.util.Scanner;

/**
* <b>function:</b> 运行客户端程序
* @author hoojo
* @createDate 2012-6-29 下午07:36:44
* @file RunClient.java
* @package com.hoo.mina.client.main
* @project ApacheMiNa
* @blog http://blog.csdn.net/IBM_hoojo
* @email hoojo_@126.com
* @version 1.0
*/
public class RunClient {

   public static void main(String[] args) {
       MinaClient client = new MinaClient();
       if (client.connect()) {
           client.send("连接服务器成功！");
           Scanner scanner = new Scanner(System.in);
           while (scanner.hasNext()) {
               client.send(scanner.next());
           }
       }
   }
}
/*create connection to server :/127.0.0.1:3456
client Sent 客户端发送消息：Hello World!
client Sent 客户端发送消息：连接服务器成功！
client Sent 客户端发送消息：连接服务器成功！
client receive a message is : 2018-01-11 11:32:06	Hello World!
client receive a message is : 2018-01-11 11:32:06	连接服务器成功！
client receive a message is : 2018-01-11 11:32:06	连接服务器成功！

我是张三。有人在吗
client Sent 客户端发送消息：我是张三。有人在吗
client receive a message is : 2018-01-11 11:32:23	我是张三。有人在吗
client receive a message is : 2018-01-11 11:32:28	连接服务器成功！
client receive a message is : 2018-01-11 11:32:28	Hello World!
client receive a message is : 2018-01-11 11:32:28	连接服务器成功！
client receive a message is : 2018-01-11 11:32:44	我是李四。我刚进来。你好张三

李四你好。你今天吃饭了没
client Sent 客户端发送消息：李四你好。你今天吃饭了没
client receive a message is : 2018-01-11 11:33:23	李四你好。你今天吃饭了没
client receive a message is : 2018-01-11 11:33:54	我每吃啊。好饿。咋办呢
李四。我有面包你吃不吃啊。
client Sent 客户端发送消息：李四。我有面包你吃不吃啊。
client receive a message is : 2018-01-11 11:34:18	李四。我有面包你吃不吃啊。
client receive a message is : 2018-01-11 11:34:39	快给我吃。饿死了。呜呜。感谢你。你是好人
不客气了。咱两个关系好。拜拜
client Sent 客户端发送消息：不客气了。咱两个关系好。拜拜
client receive a message is : 2018-01-11 11:35:00	不客气了。咱两个关系好。拜拜
client receive a message is : 2018-01-11 11:35:10	拜拜。张三*/

/*
create connection to server :/127.0.0.1:3456
client Sent 客户端发送消息：连接服务器成功！
client Sent 客户端发送消息：Hello World!
client Sent 客户端发送消息：连接服务器成功！
client receive a message is : 2018-01-11 11:32:28	连接服务器成功！
client receive a message is : 2018-01-11 11:32:28	Hello World!
client receive a message is : 2018-01-11 11:32:28	连接服务器成功！
我是李四。我刚进来。你好张三
client Sent 客户端发送消息：我是李四。我刚进来。你好张三
client receive a message is : 2018-01-11 11:32:44	我是李四。我刚进来。你好张三

client receive a message is : 2018-01-11 11:33:23	李四你好。你今天吃饭了没

我每吃啊。好饿。咋办呢
client Sent 客户端发送消息：我每吃啊。好饿。咋办呢
client receive a message is : 2018-01-11 11:33:54	我每吃啊。好饿。咋办呢
client receive a message is : 2018-01-11 11:34:18	李四。我有面包你吃不吃啊。

快给我吃。饿死了。呜呜。感谢你。你是好人
client Sent 客户端发送消息：快给我吃。饿死了。呜呜。感谢你。你是好人
client receive a message is : 2018-01-11 11:34:39	快给我吃。饿死了。呜呜。感谢你。你是好人
client receive a message is : 2018-01-11 11:35:00	不客气了。咱两个关系好。拜拜
拜拜。张三
client Sent 客户端发送消息：拜拜。张三
client receive a message is : 2018-01-11 11:35:10	拜拜。张三
*/


/*create connection to server :/127.0.0.1:3456
/127.0.0.1:52084:Hello World!
/127.0.0.1:52084:连接服务器成功！
我是客户1，有人吗
/127.0.0.1:52084:有人啊。我是客户2，你好啊
你好。我叫张三。你叫啥
/127.0.0.1:52084:我是客户2，李四
李四好。今天我请客。我张三有钱
/127.0.0.1:52084:好的。张老板
*/


/*create connection to server :/127.0.0.1:3456
/127.0.0.1:52116:我是客户1，有人吗
有人啊。我是客户2，你好啊
/127.0.0.1:52116:你好。我叫张三。你叫啥
我是客户2，李四
/127.0.0.1:52116:李四好。今天我请客。我张三有钱
好的。张老板*/
