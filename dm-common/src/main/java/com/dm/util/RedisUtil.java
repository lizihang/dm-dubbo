package com.dm.util;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * <p>创建日期：2021年06月23日 15:29</p>
 * <p>类全名：com.dm.util.RedisUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisUtil
{
	@Resource
	public RedisTemplate redisTemplate;

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 键
	 * @param value 值
	 * @param <T> 泛型
	 */
	public <T> void setCacheObject(final String key, final T value)
	{
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 键
	 * @param value 值
	 * @param timeout 超时时间
	 * @param timeUnit 时间单位
	 * @param <T> 泛型
	 */
	public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
	{
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	/**
	 * 获得缓存的基本对象。
	 * @param key 键
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> T getCacheObject(final String key)
	{
		ValueOperations<String,T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 删除单个对象
	 * @param key 键
	 * @return 是否删除成功
	 */
	public boolean deleteObject(final String key)
	{
		return redisTemplate.delete(key);
	}

	/**
	 * 删除集合对象
	 * @param collection 删除对象集合
	 * @return 删除的数量
	 */
	public long deleteObjects(final Collection collection)
	{
		return redisTemplate.delete(collection);
	}

	/**
	 * 设置超时时间
	 * @param key 键
	 * @param timeout 超时时间，单位：秒
	 * @return 是否成功
	 */
	public boolean expire(final String key, final long timeout)
	{
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置超时时间
	 * @param key 键
	 * @param timeout 超时时间
	 * @param timeUnit 时间单位
	 * @return 是否成功
	 */
	public boolean expire(final String key, final long timeout, final TimeUnit timeUnit)
	{
		return redisTemplate.expire(key, timeout, timeUnit);
	}

	/**
	 * 缓存List数据
	 * @param key 键
	 * @param dataList 值
	 * @param <T> 泛型
	 * @return ？？
	 */
	public <T> long setCacheList(final String key, final List<T> dataList)
	{
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}

	/**
	 * 获取缓存的list对象
	 * @param key 键
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> List<T> getCacheList(final String key)
	{
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * 缓存Set数据
	 * @param key 键
	 * @param dataSet 值
	 * @param <T> 泛型
	 * @return ？？
	 */
	public <T> long setCacheSet(final String key, final Set<T> dataSet)
	{
		Long count = redisTemplate.opsForSet().add(key, dataSet);
		return count == null ? 0 : count;
	}

	/**
	 * 获取缓存的set对象
	 * @param key 键
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> Set<T> getCacheSet(final String key)
	{
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 缓存Map数据
	 * @param key 键
	 * @param dataMap 值
	 * @param <T> 泛型
	 */
	public <T> void setCacheMap(final String key, final Map<String,T> dataMap)
	{
		redisTemplate.opsForHash().putAll(key, dataMap);
	}

	/**
	 * 获得缓存的Map对象
	 * @param key 键
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> Map<String,T> getCacheMap(final String key)
	{
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 往Hash中存入数据
	 * @param key 键
	 * @param hKey Hash键
	 * @param value 值
	 * @param <T> 泛型
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value)
	{
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	/**
	 * 获取Hash中的数据
	 * @param key 键
	 * @param hKey Hash键
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> T getCacheMapValue(final String key, final String hKey)
	{
		HashOperations<String,String,T> opsForHash = redisTemplate.opsForHash();
		return opsForHash.get(key, hKey);
	}

	/**
	 * 获取多个Hash中的数据
	 * @param key 键
	 * @param hKeys Hash键集合
	 * @param <T> 泛型
	 * @return 值
	 */
	public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
	{
		return redisTemplate.opsForHash().multiGet(key, hKeys);
	}

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key)
	{
		return redisTemplate.hasKey(key);
	}

	/**
	 * 获得缓存的基本对象列表
	 * @param pattern 字符串前缀
	 * @return key的集合
	 */
	public Collection<String> keys(final String pattern)
	{
		return redisTemplate.keys(pattern);
	}
}
