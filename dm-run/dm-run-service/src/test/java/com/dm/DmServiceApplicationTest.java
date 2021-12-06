package com.dm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月30日 9:58</p>
 * <p>类全名：com.dm.DmServiceApplicationTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DmServiceApplicationTest
{
	@Resource
	RedisTemplate redisTemplate;

	@Test
	public void testList()
	{
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, queue);
		ListOperations listOperations = redisTemplate.opsForList();
	}
}
