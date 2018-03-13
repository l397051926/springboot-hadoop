package com.springboot.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 09 16:25
 * @desc
 **/
public class SimpleDemo {

    private static final int SESSION_TIMEOUT = 3000;

    private ZooKeeper zk;

    private Watcher watcher= new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.toString());
        }
    };

    private void createZKInstance() throws IOException {
        zk =new ZooKeeper("192.168.187.21:2181",SimpleDemo.SESSION_TIMEOUT,this.watcher);
    }

    private void ZKOperations() throws KeeperException, InterruptedException {
        System.out.println("n1. 创建 Zookeeper 节点（znode ：zoo2 ，数据： myData2，权限：OPEN_ACL_UNSAFE,节点类型： Persistent）");
        zk.create("/zoo2","myData2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("/n2. 查看是否创建成功： ");
        System.out.println(new String(zk.getData("/zoo2",false,null)));

        System.out.println("/n3. 修改节点数据");
        zk.setData("/zoo2","shenlan212121".getBytes(),-1);

        System.out.println("/n4 查看是否修改成功");
        System.out.println(new String(zk.getData("/zoo2",false,null)));

        System.out.println("/n5. 删除节点");
        zk.delete("/zoo2",-1);

        System.out.println("/n6. 节点是否被删除");
        System.out.println("节点状态： "+zk.exists("/zoo2",false));

    }

    private void ZKClose() throws InterruptedException {
        zk.close();
    }


    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        System.out.println("zookeepertest Demo");
        System.out.println("-----------------------");

        SimpleDemo simpleDemo=new SimpleDemo();
        simpleDemo.createZKInstance();
        simpleDemo.ZKOperations();
        simpleDemo.ZKClose();


    }
}
