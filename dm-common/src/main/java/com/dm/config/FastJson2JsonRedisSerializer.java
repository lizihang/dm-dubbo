package com.dm.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 15:28</p>
 * <p>类全名：com.dm.config.FastJson2JsonRedisSerializer</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T>
{
	@SuppressWarnings("unused")
	private             ObjectMapper objectMapper    = new ObjectMapper();
	public static final Charset      DEFAULT_CHARSET = StandardCharsets.UTF_8;
	private             Class<T>     clazz;

	static
	{
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
	}

	public FastJson2JsonRedisSerializer(Class<T> clazz)
	{
		super();
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException
	{
		if (t == null)
		{
			return new byte[0];
		}
		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException
	{
		if (bytes == null || bytes.length <= 0)
		{
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		return JSON.parseObject(str, clazz);
	}

	public void setObjectMapper(ObjectMapper objectMapper)
	{
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
		this.objectMapper = objectMapper;
	}

	protected JavaType getJavaType(Class<?> clazz)
	{
		return TypeFactory.defaultInstance().constructType(clazz);
	}
}
