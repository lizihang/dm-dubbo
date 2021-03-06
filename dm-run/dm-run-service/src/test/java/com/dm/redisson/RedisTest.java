package com.dm.redisson;

import com.dm.DmServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
/**
 * <p>标题：Redis测试类</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月17日 14:42</p>
 * <p>类全名：com.dm.redisson.RedisTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class RedisTest
{
	@Resource
	RedisTemplate<String,String> redisTemplate;

	@Test
	public void testRedisString()
	{
		ValueOperations<String,String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("redis-key:1", "value1");
		opsForValue.set("redis-key:2", "value1");
		opsForValue.set("redis-key:3", "value1");
	}

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
		Long delete = opsForHash.delete("dm_user", "user_id_1");
		System.out.println("delete:" + delete);
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
}
