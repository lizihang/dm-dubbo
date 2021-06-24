package com.dm.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 15:36</p>
 * <p>类全名：com.dm.system.config.ResourcesConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class ResourcesConfig extends WebMvcConfigurationSupport
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		// 路径最后一定要加"/"
		registry.addResourceHandler("/avatar/**").addResourceLocations("file:/D:/dm/upload/avatar/");
		super.addResourceHandlers(registry);
	}
}
