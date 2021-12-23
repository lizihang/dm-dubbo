package com.dm.redisson;

import com.dm.DmServiceApplication;
import com.dm.system.po.DmUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
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
	public void testRedissonBucket()
	{
		RBucket<String> bucket = redisson.getBucket("user-1");
		bucket.set("value-1");
		boolean b1 = bucket.compareAndSet("value-1", "value-2");
		boolean b2 = bucket.compareAndSet("value-1", "value-3");
		String s = bucket.get();
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(s);
	}

	@Test
	public void testRedissonList()
	{
		RList<DmUser> list = redisson.getList("user-list");
		DmUser user1 = new DmUser(1, "user1", "pwd1", "tony", "tony@163.com", "18866668888", 1, null, "70");
		DmUser user2 = new DmUser(2, "user2", "pwd2", "tony", "tony@163.com", "18866668888", 1, null, "70");
		DmUser user3 = new DmUser(3, "user3", "pwd3", "tony", "tony@163.com", "18866668888", 1, null, "70");
		// boolean add = list.add(user);
		// System.out.println(add);
		list.add(user1);
		int i = list.addBefore(user1, user2);
		int j = list.addAfter(user1, user3);
		list.expire(20, TimeUnit.SECONDS);
		System.out.println(i);
		System.out.println(j);
	}

	@Test
	public void testRedissonMap()
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
