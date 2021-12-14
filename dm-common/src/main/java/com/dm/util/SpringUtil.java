package com.dm.util;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.apache.dubbo.config.spring.beans.factory.annotation.ServiceAnnotationBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
 * <p>创建日期：2021年12月13日 11:25</p>
 * <p>类全名：com.dm.util.SpringUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SuppressWarnings("unchecked")
@Component
public class SpringUtil implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException
	{
		SpringUtil.applicationContext = applicationContext;
	}

	public static <T> T getBean(String beanName)
	{
		if (applicationContext.containsBean(beanName))
		{
			return (T) applicationContext.getBean(beanName);
		} else
		{
			return null;
		}
	}

	public static <T> Map<String,T> getBeansOfType(Class<T> baseType)
	{
		return applicationContext.getBeansOfType(baseType);
	}

	public static <T> T getDubboBean(Class<T> type)
	{
		ReferenceAnnotationBeanPostProcessor dubboContext = applicationContext.getBean(ReferenceAnnotationBeanPostProcessor.class);
		Collection<ReferenceBean<?>> referenceBeans = dubboContext.getReferenceBeans();
		for (ReferenceBean<?> referenceBean : referenceBeans)
		{
			Class<?> classType = referenceBean.getObjectType();
			if (classType == type)
			{
				return (T) referenceBean.getObject();
			}
		}
		return null;
	}
}
