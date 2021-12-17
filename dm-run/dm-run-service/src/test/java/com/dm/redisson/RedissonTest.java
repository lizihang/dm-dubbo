package com.dm.redisson;

import com.dm.DmServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
/**
 * <p>标题：Redisson测试类</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月17日 14:41</p>
 * <p>类全名：com.dm.redisson.RedissonTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class RedissonTest
{
	@Resource
	RedissonClient redisson;

	@Test
	public void testRedisson()
	{
		RMap<String,String> userMap = redisson.getMap("redisson_user");
		String o1 = userMap.put("user1", "user1 password1");
		String o2 = userMap.put("user1", "user1 password1 put 2");
		boolean b1 = userMap.fastPut("user2", "fastput user2 password2");
		boolean b2 = userMap.fastPut("user2", "fastput2 user2 password2");
		System.out.println(o1 + "==" + o2 + "==" + b1 + "==" + b2);
	}

	@Test
	public void testRedissonLock()
	{
		RLock testLock = redisson.getLock("testLock");
		testLock.lock();
		try
		{
			System.out.println("redisson lock");
			Thread.sleep(10000);
			testLock.lock();
			System.out.println("lock 2");
			Thread.sleep(10000);
			testLock.unlock();
		} catch (Exception e)
		{
			System.out.println("there is some exception");
		} finally
		{
			testLock.unlock();
			System.out.println("redisson unlock");
		}
	}

	@Test
	public void testRedissonLock2()
	{
		RLock testLock2 = redisson.getLock("testLock2");
		new Thread(() -> {
			testLock2.lock();
			try
			{
				System.out.println(Thread.currentThread().getName() + " redisson lock");
				Thread.sleep(10000);
			} catch (Exception e)
			{
				System.out.println("there is some exception");
			} finally
			{
				testLock2.unlock();
				System.out.println(Thread.currentThread().getName() + " redisson unlock");
			}
		}, "thread-1").start();
		new Thread(() -> {
			testLock2.lock();
			try
			{
				System.out.println(Thread.currentThread().getName() + " redisson lock");
				Thread.sleep(10000);
			} catch (Exception e)
			{
				System.out.println("there is some exception");
			} finally
			{
				testLock2.unlock();
				System.out.println(Thread.currentThread().getName() + " redisson unlock");
			}
		}, "thread-2").start();
	}
}
