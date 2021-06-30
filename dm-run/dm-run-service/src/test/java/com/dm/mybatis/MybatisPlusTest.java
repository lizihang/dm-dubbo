package com.dm.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.DmServiceApplication;
import com.dm.system.dao.UserDAO;
import com.dm.system.po.DmUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月30日 10:47</p>
 * <p>类全名：com.dm.mybatis.MybatisPlusTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class MybatisPlusTest
{
	@Resource
	UserDAO userDAO;

	/**
	 *
	 */
	@Test
	public void testInsert()
	{
		DmUser user = new DmUser();
		user.setUsername("username");
		user.setPassword("password");
		user.setNickname("nickname");
		userDAO.insert(user);
		System.out.println("hello:" + user);
	}

	/**
	 *
	 */
	@Test
	public void testUpdate()
	{
		DmUser user = new DmUser();
		user.setId(12);
		user.setUsername("username2");
		user.setPassword("password2");
		user.setNickname("nickname2");
		userDAO.updateById(user);
	}

	/**
	 *
	 */
	@Test
	public void testDelete()
	{
		userDAO.deleteById(13);
	}

	/**
	 *
	 */
	@Test
	public void testSelect()
	{
		Page<DmUser> page = new Page<>(1, 5);
		IPage<DmUser> data = userDAO.selectPage(page, null);
		System.out.println("total:" + data.getTotal());
		List<DmUser> records = data.getRecords();
		records.forEach(System.out::println);
	}

	/**
	 *
	 */
	@Test
	public void testSelectWrapper()
	{
		Page<DmUser> page = new Page<>(2, 5);
		QueryWrapper<DmUser> wrapper = new QueryWrapper<>();
		wrapper.like("username", "user");
		IPage<DmUser> data = userDAO.selectPage(page, wrapper);
		System.out.println("total:" + data.getTotal());
		List<DmUser> records = data.getRecords();
		records.forEach(System.out::println);
	}
}
