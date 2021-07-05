package com.dm.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dm.generator.engine.MyVelocityTemplateEngine;
import com.dm.po.BasePO;

import java.util.Scanner;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 优点：
 * 		1.根据表生成各个层的类，节省时间
 * 		2.可以根据模板生成，例如模板:/templates/entity.java.vm
 * 		3.复杂一点可以做到将代码生成到不同的模块下
 * 缺点：
 * 		1.dubbo分布式注解不能设置
 * 		2.controller层RequestMapping不够灵活
 * 		3.service层默认继承IService
 * 		4.mapper.xml想放到resource目录下
 * 		5.上面1,2,3的问题可以通过自己写模板后修改
 * 待优化：
 * 		1.根据输入的模块路径，将类分别生成到其目录下（xxx-api,xxx-service,xxx-ui）
 * 		2.修改java文件生成模板
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年07月05日 19:26</p>
 * <p>类全名：com.dm.generator.CodeGenerator</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CodeGenerator
{
	public static void main(String[] args)
	{
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir") + "/" + scanner("模块路径");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("lizh");
		gc.setOpen(false);
		// Date类型用java.util的
		gc.setDateType(DateType.ONLY_DATE);
		// 不覆盖已有文件
		gc.setFileOverride(false);
		// 去掉接口的I
		gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dm?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		mpg.setDataSource(dsc);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(scanner("模块名"));
		pc.setParent("com.dm");
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setMapper("dao");
		pc.setEntity("po");
		pc.setXml("dao.xml");
		mpg.setPackageInfo(pc);
		// 模板配置
		TemplateConfig tc = new TemplateConfig();
		// 不填就是不生成controller，填自己路径就是根据自己模板生成
		tc.setController(null);
		tc.setEntity("/templates/entity.java.vm");
		mpg.setTemplate(tc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 配置表，可以多个
		strategy.setInclude("dm_user");
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(BasePO.class);
		// TODO 作用？
		strategy.setSuperEntityColumns("createUser", "createTime", "modifyUser", "modifyTime");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 自动生成的是1L，设置为false，生成类以后手动生成序列化ID
		strategy.setEntitySerialVersionUID(false);
		mpg.setStrategy(strategy);
		// 设置engine
		mpg.setTemplateEngine(new MyVelocityTemplateEngine());
		// 执行
		mpg.execute();
	}

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入" + tip + "：");
		if (scanner.hasNext())
		{
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt))
			{
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}
}
