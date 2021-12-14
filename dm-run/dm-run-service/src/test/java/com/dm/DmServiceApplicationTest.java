package com.dm;

import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;
import com.dm.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class DmServiceApplicationTest
{
	@Resource
	RedisTemplate<String,String> redisTemplate;
	@Resource
	UserService                  userService;
	@Resource
	RedissonClient               redisson;

	@Test
	public void testRedisHash()
	{
		HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
		opsForHash.put("dm_user", "user_id_1", "value_1");
		opsForHash.put("dm_user", "user_id_2", "value_2");
		opsForHash.put("dm_user", "user_id_3", "value_3");
		opsForHash.put("dm_user", "user_id_4", "value_4");
		opsForHash.put("dm_user", "user_id_5", "value_5");
		Set<String> keys = opsForHash.keys("dm_user");
		for (String key : keys)
		{
			System.out.println("key:" + key);
		}
		Object v1 = opsForHash.get("dm_user", "user_id_1");
		System.out.println("v1:" + v1);
	}

	@Test
	public void testRedisHash2()
	{
		HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
		opsForHash.put("dm_user", "user_id_1", "new_value_1");
	}

	@Test
	public void testRedisListPush()
	{
		ListOperations<String,String> opsForList = redisTemplate.opsForList();
		for (int i = 0; i < 5; i++)
		{
			opsForList.leftPush("k1", "v" + i);
		}
	}

	@Test
	public void testRedisListPop()
	{
		ListOperations<String,String> opsForList = redisTemplate.opsForList();
		for (int i = 0; i < 5; i++)
		{
			System.out.println(opsForList.leftPop("k1"));
		}
	}

	@Test
	public void testSpringUtil()
	{
		Map<String,UserService> userServiceMap = SpringUtil.getBeansOfType(UserService.class);
		System.out.println(userServiceMap.size());
		for (String key : userServiceMap.keySet())
		{
			System.out.println(key);
			UserService userService = userServiceMap.get(key);
			DmUser user = userService.queryUserByUsername("admin");
			System.out.println(user);
		}
	}

	@Test
	public void testTransaction()
	{
		DmUser user = new DmUser();
		user.setId(12);
		user.setNickname("testTransaction");
		try
		{
			userService.updateUser(user);
		} catch (Exception e)
		{
			System.out.println("发生了异常！");
		}
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

	@Test
	public void testReflect() throws Exception
	{
		Class<?> aClass = Class.forName("com.dm.system.po.DmUser");
		Constructor<?> constructor = aClass.getConstructor();
		Object o = constructor.newInstance();
		Field[] declaredFields = aClass.getDeclaredFields();
		for (Field field : declaredFields)
		{
			System.out.println(field.getName());
		}
		System.out.println("===========");
		Method[] declaredMethods = aClass.getDeclaredMethods();
		for (Method method : declaredMethods)
		{
			System.out.println(method.getName());
		}
	}
}
