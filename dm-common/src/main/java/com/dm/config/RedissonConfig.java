package com.dm.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月14日 14:13</p>
 * <p>类全名：com.dm.config.RedissonConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class RedissonConfig
{
	@Bean
	RedissonClient redisson(){
		//1、创建配置
		Config config = new Config();
		config.useSingleServer()
				.setAddress("redis://127.0.0.1:6379")
				.setDatabase(1);
		return Redisson.create(config);
	}
}
