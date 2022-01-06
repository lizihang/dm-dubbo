package com.dm;

import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;
import com.dm.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
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
	UserService userService;

	@Test
	public void testBeanNameAware()
	{
		String beanName = userService.getBeanName();
		System.out.println(beanName);
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
