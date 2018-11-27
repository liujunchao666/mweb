package com.zoo;

import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZookTest {

    // 根节点

    public static void main(String[] args) throws Exception {
      
        ZooKeeper zk = new ZooKeeper("localhost:2181", 30000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("状态:" +event.getType()+":"+event.getWrapper()+":"+event.getPath());
            }
        });
        zk.create("/root", "mydata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/root/com.ibm.Service1","192.168.0.1".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/root/com.ibm.Service2","192.168.0.2".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        List<String> children = zk.getChildren("/root",true);
        for (String string : children) { 
			System.out.println(string);
		}
        byte[] data = zk.getData("/root/com.ibm.Service1", true, null);
		System.out.println(new String(data));
		
		 byte[] data2 = zk.getData("/root/com.ibm.Service2", true, null);
		 System.out.println(new String(data2));


        zk.setData("/root/com.ibm.Service1","192.168.0.3".getBytes(), -1);
        
        byte[] data3 = zk.getData("/root/com.ibm.Service1", true, null);
		System.out.println(new String(data3));
		
		
        zk.delete("/root", -1);
        zk.close();
    }
}
