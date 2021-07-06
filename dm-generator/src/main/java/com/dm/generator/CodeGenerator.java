package com.dm.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dm.generator.constant.GeneratorConstant;
import com.dm.generator.engine.MyVelocityTemplateEngine;
import com.dm.po.BasePO;

import java.util.ArrayList;
import java.util.List;
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
 * 		1.只能生成单表
 * 		2.PO对象如何排除BasePO的字段？
 * 		3.controller层RequestMapping
 * 		4.此版本只支持swagger2
 * 已优化：
 * 		1.根据输入的模块路径，将类分别生成到其目录下（xxx-api,xxx-service,xxx-ui）
 * 		2.xxx-impl模块service注解@DubboService
 * 		3.修改文件生成模板，达到定制目的
 * 待优化：
 * 		1.mapper.xml想放到impl模块resource/com/dm/mapper目录下
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
	private static final String[] modules = new String[] { "api", "impl", "ui" };

	public static void main(String[] args)
	{
		setGeneratorConfig();
	}

	public static void demo()
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

	public static void setGeneratorConfig()
	{
		System.out.println("请输入模块名称（例：dm-generator）：");
		String modulePath = scanner2();
		System.out.println("请输入模块包名：");
		String moduleName = scanner2();
		System.out.println("请输入表名（多个表用英文逗号分割）：");
		String tableNames = scanner2();
		for (String module : modules)
		{
			String mPath = modulePath + "-" + module;
			System.out.println("是否生成<" + mPath + ">模块代码？输入quit跳过此模块");
			String operate = scanner2();
			if ("quit".equals(operate))
			{
				continue;
			}
			System.out.println("开始生成<" + mPath + ">模块代码");
			singleGeneratorExecute(mPath, moduleName, tableNames);
			System.out.println("<" + mPath + ">模块代码生成完毕");
		}
		System.out.println("所有模块代码生成完毕");
	}

	private static void singleGeneratorExecute(String modulePath, String moduleName, String tableNames)
	{
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		mpg.setGlobalConfig(getGlobalConfig(modulePath));
		// 数据源配置
		mpg.setDataSource(getDataSourceConfig());
		// 包配置
		mpg.setPackageInfo(getPackageConfig(moduleName));
		// 模板配置
		String module = modulePath.substring(modulePath.lastIndexOf("-") + 1);
		mpg.setTemplate(getTemplateConfig(module));
		// 策略配置
		mpg.setStrategy(getStrategyConfig(tableNames));
		// 设置engine
		mpg.setTemplateEngine(new MyVelocityTemplateEngine());
		// 配置mapper.xml输出路径
		if (GeneratorConstant.MODULE_IMPL.equals(module))
		{
			mpg.setCfg(getInjectionConfig(modulePath));
		}
		// 执行
		mpg.execute();
	}

	private static GlobalConfig getGlobalConfig(String modulePath)
	{
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir") + "/" + modulePath;
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("lizh");
		gc.setOpen(false);
		// Date类型用java.util的
		gc.setDateType(DateType.ONLY_DATE);
		// 不覆盖已有文件
		gc.setFileOverride(false);
		// 去掉接口的I
		if (modulePath.endsWith("-ui"))
		{
			gc.setServiceName("%sUIService");
			gc.setServiceImplName("%sUIServiceImpl");
		} else
		{
			gc.setServiceName("%sService");
			gc.setServiceImplName("%sServiceImpl");
		}
		// 配置mapper名称
		gc.setMapperName("%sDAO");
		return gc;
	}

	private static DataSourceConfig getDataSourceConfig()
	{
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dm?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		return dsc;
	}

	private static PackageConfig getPackageConfig(String moduleName)
	{
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(moduleName);
		pc.setParent("com.dm");
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setMapper("dao");
		pc.setEntity("po");
		pc.setXml("dao.xml");
		return pc;
	}

	private static TemplateConfig getTemplateConfig(String module)
	{
		// 模板配置
		TemplateConfig tc = new TemplateConfig();
		// 如果不需要生成，参数为null。填自己路径就是根据自己模板生成
		switch (module)
		{
		case GeneratorConstant.MODULE_API:
			tc.setController(null);
			tc.setService("/templates/service.java");
			tc.setServiceImpl(null);
			tc.setEntity("/templates/entity.java");
			tc.setMapper(null);
			tc.setXml(null);
			break;
		case GeneratorConstant.MODULE_IMPL:
			tc.setController(null);
			tc.setService(null);
			tc.setServiceImpl("/templates/serviceImpl.java");
			tc.setEntity(null);
			tc.setMapper("/templates/mapper.java");
			// 输出路径单独配置
			tc.setXml(null);
			break;
		case GeneratorConstant.MODULE_UI:
			tc.setController("/templates/controller.java");
			tc.setService("/templates/uiService.java");
			tc.setServiceImpl("/templates/uiServiceImpl.java");
			tc.setEntity(null);
			tc.setMapper(null);
			tc.setXml(null);
			break;
		}
		return tc;
	}

	private static StrategyConfig getStrategyConfig(String tableNames)
	{
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 配置表，可以多个
		strategy.setInclude(tableNames.split(","));
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(BasePO.class);
		// TODO 作用？
		strategy.setSuperEntityColumns("createUser", "createTime", "modifyUser", "modifyTime");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 自动生成的是1L，设置为false，生成类以后手动生成序列化ID
		strategy.setEntitySerialVersionUID(false);
		return strategy;
	}

	private static InjectionConfig getInjectionConfig(String modulePath)
	{
		InjectionConfig cfg = new InjectionConfig()
		{
			@Override
			public void initMap()
			{
				// to do nothing
			}
		};
		String projectPath = System.getProperty("user.dir") + "/" + modulePath;
		String templatePath = "/templates/mapper.xml.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath)
		{
			@Override
			public String outputFile(TableInfo tableInfo)
			{
				// 自定义输出路径
				return projectPath + "/src/main/resources/com/dm/mapper/" + tableInfo.getEntityName() + "DAO" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		return cfg;
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

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner2()
	{
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext())
		{
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt))
			{
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的参数！");
	}
}
