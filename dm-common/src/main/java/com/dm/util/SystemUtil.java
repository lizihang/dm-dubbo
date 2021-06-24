package com.dm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 19:04</p>
 * <p>类全名：com.dm.util.SystemUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class SystemUtil
{
	private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

	public static void getSystemInfo()
	{
		logger.info("user.name = " + System.getProperty("user.name"));
		logger.info("user.home = " + System.getProperty("user.home"));
		logger.info("user.dir = " + System.getProperty("user.dir"));
		logger.info("java.version = " + System.getProperty("java.version"));
		logger.info("java.vendor = " + System.getProperty("java.vendor"));
		logger.info("java.class.path = " + System.getProperty("java.class.path"));
		logger.info("java.library.path = " + System.getProperty("java.library.path"));
		logger.info("java.home = " + System.getProperty("java.home"));
		logger.info("os.name = " + System.getProperty("os.name"));
		logger.info("os.arch = " + System.getProperty("os.arch"));
		logger.info("os.version = " + System.getProperty("os.version"));
	}
}
