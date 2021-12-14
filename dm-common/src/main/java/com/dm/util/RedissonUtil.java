package com.dm.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月13日 15:47</p>
 * <p>类全名：com.dm.util.RedissonUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class RedissonUtil
{
	@Resource
	public RedissonClient redisson;

	public void testTryLock()
	{
		RLock lock = redisson.getLock("anyLock");
		boolean locked = lock.tryLock();
		if (locked)
		{
			try
			{
				System.out.println("here");
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				lock.unlock();
			}
		}
	}
}
