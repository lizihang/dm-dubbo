package com.dm.sql;

import com.dm.DmServiceApplication;
import com.dm.system.po.SQLUser;
import com.dm.system.service.SQLService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月16日 9:22</p>
 * <p>类全名：com.dm.sql.SQLTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class SQLTest
{
	@Resource
	SQLService sqlService;
	private static final Random random = new Random();

	/**
	 * 向user表随机插入数据，用于sql优化
	 */
	@Test
	public void insertRandomData()
	{
		List<SQLUser> data = new ArrayList<>();
		for (int i = 0; i < 9000; i++)
		{
			// 1.随机生成对象
			data = buildRandomData();
			// 2.批量插入
			boolean b = sqlService.saveBatch(data);
			// 3.清空data
			data.clear();
			// 4.打印信息
			System.out.println("打印第" + (i + 1) + "页的1000条数据");
		}
	}

	private List<SQLUser> buildRandomData()
	{
		List<SQLUser> result = new ArrayList<>();
		for (int i = 0; i < 1000; i++)
		{
			SQLUser user = new SQLUser();
			user.setUsername(RandomStringUtils.random(randomInt(), true, false));
			user.setNickname(RandomStringUtils.random(randomInt(), true, false));
			user.setPassword(RandomStringUtils.random(randomInt(), true, true));
			user.setEmail(RandomStringUtils.random(randomInt(), true, true));
			result.add(user);
		}
		return result;
	}

	private int randomInt()
	{
		return random.nextInt(10) + 10;
	}


}
