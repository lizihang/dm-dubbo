package com.dm;

import com.dm.util.SystemUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月24日 9:28</p>
 * <p>类全名：com.dm.DmUIApplication</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootApplication
@EnableDubbo    // 开启dubbo
public class DmUIApplication
{
	public static void main(String[] args)
	{
		SystemUtil.getSystemInfo();
		SpringApplication.run(DmUIApplication.class, args);
	}
}
