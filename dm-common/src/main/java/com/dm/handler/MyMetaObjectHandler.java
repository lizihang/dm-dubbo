package com.dm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dm.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月30日 9:30</p>
 * <p>类全名：com.dm.handler.MyMetaObjectHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler
{
	@Override
	public void insertFill(MetaObject metaObject)
	{
		log.info("start insert fill ....");
		Date date = DateUtil.getServerDate();
		this.strictInsertFill(metaObject, "createTime", Date.class, date);
		this.strictInsertFill(metaObject, "modifyTime", Date.class, date);
		this.strictInsertFill(metaObject, "status", String.class, "00");
		// 对象中不存在的字段，不会报错
		this.strictInsertFill(metaObject, "status2", String.class, "00");
	}

	@Override
	public void updateFill(MetaObject metaObject)
	{
		log.info("start update fill ....");
		Date date = DateUtil.getServerDate();
		this.strictInsertFill(metaObject, "modifyTime", Date.class, date);
	}
}
