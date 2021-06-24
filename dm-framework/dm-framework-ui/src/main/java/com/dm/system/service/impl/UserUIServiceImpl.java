package com.dm.system.service.impl;

import com.dm.system.service.UserUIService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;

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
	public List<DmUser> queryListUI(DmUserQueryParams params)
	{
		return userService.queryList(params);
	}

	@Override
	public int queryTotalUI(DmUserQueryParams params)
	{
		return userService.queryTotal(params);
	}

	@Override
	public DmUser queryUserByUserNameUI(String username)
	{
		return userService.queryUserByUserName(username);
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
	public void deleteUserByIdUI(int id)
	{
		userService.deleteUserById(id);
	}
}
