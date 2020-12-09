package com.fastree.springboot.zookeeper;

import com.fastree.springboot.zookeeper.factory.ClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class ZookeeperApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void createNode() {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        try {
            client.start();
            String destPath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath("/create/id_", "create".getBytes());
            System.out.println(destPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    void readNode() {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        try {
            client.start();
            String zkPath = "/create/node1";
            Stat stat = client.checkExists().forPath(zkPath);
            if (stat != null) {
                byte[] bytes = client.getData().forPath(zkPath);
                System.out.println(new String(bytes));
                String parentPath = "/create";
                List<String> children = client.getChildren().forPath(parentPath);
                children.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    void updateNode() {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        try {
            client.start();
            String zkPath = "/create";
//			Stat stat = client.setData().forPath(zkPath, "node11".getBytes());
            client.setData()
                    .inBackground(new BackgroundCallback() {
                        @Override
                        public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                            Object context = event.getContext();
                            System.out.println(event);
                        }
                    }, "I am context")
                    .forPath(zkPath);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    void deleteNode() {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        try {
            client.start();
            String zkPath = "/create";
            client.delete().deletingChildrenIfNeeded().forPath(zkPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    void watcherListener() {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        try {
            client.start();
            CuratorWatcher curatorWatcher = new CuratorWatcher() {
                @Override
                public void process(WatchedEvent event) throws Exception {

                }
            };
            Watcher watcher = new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    String path = event.getPath();
                    Event.KeeperState state = event.getState();
                    Event.EventType type = event.getType();

                    System.out.println(path);
                    System.out.println(state.getIntValue());
                    System.out.println(type.getIntValue());
                }
            };
            byte[] content = client.getData()
                    .usingWatcher(watcher).forPath("/create/node1");
            System.out.println(new String(content));
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    void nodeCacheListener() throws Exception {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        String path = "/create";
        NodeCache nodeCache = new NodeCache(client, path, true);
        NodeCacheListener listener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData currentData = nodeCache.getCurrentData();
                log.info("ZNode节点状态改变，path={}", currentData.getPath());
                log.info("ZNode节点状态改变，data={}", new String(currentData.getData(), "UTF-8"));
                log.info("ZNode节点状态改变，stat={}", currentData.getStat());
            }
        };
        nodeCache.getListenable().addListener(listener);
        nodeCache.start(true);

        client.setData().forPath(path, "第1次更改内容".getBytes());
        client.setData().forPath(path, "第2次更改内容".getBytes());
        client.setData().forPath(path, "第3次更改内容".getBytes());
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void testCuratorCache() throws Exception {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        client.start();
        CuratorCache curatorCache = CuratorCache.build(client, "/client");
        Listenable<CuratorCacheListener> listenable = curatorCache.listenable();
        listenable.addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {
                System.out.println(type.name());
                System.out.println(new String(oldData.getData()));
                System.out.println(new String(data.getData()));
            }
        });

        curatorCache.start();

        Thread.sleep(500000);

        curatorCache.close();
        client.close();

    }

    @Test
    void testSetBarrier() throws IOException {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        client.start();
        DistributedBarrier barrier = new DistributedBarrier(client, "/barrier_test");

        try {
            barrier.setBarrier();
            barrier.waitOnBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    @Test
    void testRemoveBarrier() throws IOException {
        CuratorFramework client = ClientFactory.createSimple("192.168.188.128:2181");
        client.start();
        DistributedBarrier barrier = new DistributedBarrier(client, "/barrier_test");

        try {
            barrier.removeBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
    }


}
