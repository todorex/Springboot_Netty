package com.todorex;

import com.todorex.config.NettyProperties;
import com.todorex.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 * @author rex
 */
@SpringBootApplication
@EnableConfigurationProperties(NettyProperties.class)
public class SpringbootNettyApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootNettyApplication.class, args);
		Server server = context.getBean(Server.class);
		server.start();
	}
}
