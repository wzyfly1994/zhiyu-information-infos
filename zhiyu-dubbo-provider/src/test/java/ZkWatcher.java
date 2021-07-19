import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZkWatcher implements Watcher {
    /**
     * 集群地址
     */
    // private static final String CONNECT_ADDRES =
    // "192.168.110.155:2181,192.168.110.156:2181,192.168.110.157:2181";
    private static final String CONNECT_ADDRES = "127.0.0.1:2181";
    private static ZooKeeper zooKeeper;
    /**
     * 超时时间
     */
    private static final int SESSIONTIME = 2000;

    public ZkWatcher() {
        createConnection(CONNECT_ADDRES, SESSIONTIME);
    }

    // zk节点、发生变更、删除、修改 、 新增 事件通知
    @Override
    public void process(WatchedEvent event) {
        KeeperState keeperState = event.getState();
        // 事件類型
        EventType eventType = event.getType();
        // 节点名称
        String path = event.getPath();
        System.out.println(
                "#####process()####调用####keeperState:" + keeperState + ",eventType:" + eventType + ",path:" + path);
        if (KeeperState.SyncConnected == keeperState) {
            // 连接类型
            if (EventType.None == eventType) {
                // 建立zk连接
                System.out.println("建立zk连接成功!");
            }
            // 创建类型
            if (EventType.NodeCreated == eventType) {
                System.out.println("####事件通知,当前创建一个新的节点####路径:" + path);
            }
            // 修改類型
            if (EventType.NodeDataChanged == eventType) {
                System.out.println("####事件通知,当前创建一个修改节点####路径:" + path);
            }
            // 删除类型
            if (EventType.NodeDeleted == eventType) {
                System.out.println("####事件通知,当前创建一个删除节点####路径:" + path);
            }
        }
        System.out.println("####################################################");
        System.out.println();
    }

    // 创建zk连接
    public void createConnection(String addres, int sessionTimeout) {
        try {
            zooKeeper = new ZooKeeper(addres, sessionTimeout, this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 创建节点
    public void createNode(String path, String data) {
        try {
            String result = zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点成功....result:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 修改节点
    public void updateNode(String path, String data) {
        try {
            zooKeeper.setData(path, data.getBytes(), -1);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    // 删除节点
    public void deleNode(String path) {
        try {
            zooKeeper.delete(path, -1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void close() {
        try {
            if (zooKeeper != null)
                zooKeeper.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkWatcher zkWatcher = new ZkWatcher();
        String path = "/parent1";
        zkWatcher.createNode(path, "6452852");
        //为true标示节点受监听，有事件通知
        zooKeeper.exists(path, true);
//		zkWatcher.updateNode(path, "88888");
        //zkWatcher.deleNode(path);
        zkWatcher.close();
    }

}
