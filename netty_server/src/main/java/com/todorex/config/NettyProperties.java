package com.todorex.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Netty基本属性
 * @Author rex
 * 2018/7/25
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private int tcpPort;

    private int bossCount;

    private int workerCount;

    private boolean keepAlive;

    private int backlog;
}
