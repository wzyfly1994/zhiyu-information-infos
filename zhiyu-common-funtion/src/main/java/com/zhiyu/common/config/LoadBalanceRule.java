package com.zhiyu.common.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.consul.discovery.ConsulServer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 负载均衡规则
 *
 * @author wengzhiyu
 * @date 2021/1/14
 */
public class LoadBalanceRule extends AbstractLoadBalancerRule {


    private String tag = "pay-instance1";

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;
            while (server == null) {
                if (Thread.interrupted()) {
                    return null;
                }
                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                if (allList.size() == 0) {
                    return null;
                }
                List<Server> availableList = availableList(upList);
                if (availableList.size() == 0) {
                    return null;
                }
                int index = ThreadLocalRandom.current().nextInt(availableList.size());
                server = availableList.get(index);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }
                    server = null;
                    Thread.yield();
                }
            }
            return server;
        }
    }

    private List<Server> availableList(List<Server> upList) {
        return getConsulServerList(upList);
    }


    private List<Server> getConsulServerList(List<Server> upList) {
        return upList.stream().filter(server -> {
            ConsulServer consulServer = (ConsulServer) server;
            Map<String, String> metadata = consulServer.getMetadata();
            return tag.equals(metadata.get("profile"));
        }).collect(Collectors.toList());
    }

//    private List<Server> getEurekaServerList(List<Server> upList) {
//        List<Server> availableList = new ArrayList<>();
//        for (Server up : upList) {
//            InstanceInfo instanceInfo = (InstanceInfo) ReflectUtil.getFieldValue(up, "instanceInfo");
//            Map<String, String> metadata = instanceInfo.getMetadata();
//            String routingTag = metadata.get("routingTag");
//            if (tag.equals(routingTag)) {
//                availableList.add(up);
//            }
//        }
//        return availableList;
//    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
