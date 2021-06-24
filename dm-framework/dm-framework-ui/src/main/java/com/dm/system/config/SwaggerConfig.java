package com.dm.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 15:41</p>
 * <p>类全名：com.dm.system.config.SwaggerConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig
{
	@Bean
	public Docket docket(Environment env)
	{
		//获取项目环境
		Profiles profiles = Profiles.of("dev");
		//判断项目是否处于此环境下
		boolean flag = env.acceptsProfiles(profiles);
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.enable(flag)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.dm"))
				.build();
	}

	private ApiInfo apiInfo()
	{
		Contact contact = new Contact("dm", "www.dm.com", "dm@163.com");
		return new ApiInfo(
				"学习swagger3",
				"swagger3描述",
				"v1.0",
				"www.dm.com",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
}
