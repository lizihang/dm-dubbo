package com.dm.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;
import com.dm.system.service.UserUIService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 19:41</p>
 * <p>类全名：com.dm.com.dm.system.service.impl.UserUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class UserUIServiceImpl implements UserUIService
{
	@DubboReference
	UserService userService;

	@Override
	public Page<DmUser> queryPageUI(DmUserQueryParams params)
	{
		return userService.queryPage(params);
	}

	@Override
	public DmUser queryUserUI(DmUserQueryParams params)
	{
		/*
		查询数据库中不存在的数据，会有缓存穿透问题，
		解决办法：
		1.将空对象也放入缓存，设置过期时间。
		之后再查询此username不存在的数据时，从缓存返回，减少数据库访问
		2.布隆过滤器
		*/
		/*if (user != null)
		{
			// 3.将对象存入缓存
			redisUtil.setCacheObject(key, user);
		} else
		{
			// 将空对象存入缓存
			redisUtil.setCacheObject(key, null, 600, TimeUnit.SECONDS);
		}
		return user;*/
		return userService.queryUser(params);
	}

	@Override
	public DmUser queryUserByUsernameUI(String username)
	{
		return userService.queryUserByUsername(username);
	}

	@Override
	public void registerUI(DmUser user)
	{
		userService.register(user);
	}

	@Override
	public void updateUserUI(DmUser user)
	{
		userService.updateUser(user);
	}

	@Override
	public void deleteUserByLogicUI(int id)
	{
		userService.deleteUserByLogic(id);
	}

	@Override
	public void deleteUserUI(int id)
	{
		userService.deleteUser(id);
	}
}
