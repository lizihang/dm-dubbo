package com.dm.system.service.impl;

import com.dm.constant.Constants;
import com.dm.system.dao.UserDAO;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;
import com.dm.util.DateUtil;
import com.dm.util.RedisUtil;
import org.apache.dubbo.apidocs.annotations.ApiDoc;
import org.apache.dubbo.apidocs.annotations.ApiModule;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 19:16</p>
 * <p>类全名：com.dm.system.service.impl.UserServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@ApiModule(value = "用户service", apiInterface = UserService.class)
@DubboService
public class UserServiceImpl implements UserService
{
	@Resource
	UserDAO   userDAO;
	@Resource
	RedisUtil redisUtil;

	@ApiDoc(value = "查询用户列表", description = "查询用户列表描述", responseClassDescription = "用户列表")
	@Override
	public List<DmUser> queryList(DmUserQueryParams params)
	{
		// PageHelper 分页查询，放在查询前面
		// PageHelper.startPage(params.getPageNum(), params.getPageSize());
		List<DmUser> data = userDAO.queryList(params);
		data.forEach(user -> user.setPassword(null));
		return data;
	}

	@Override
	public int queryTotal(DmUserQueryParams params)
	{
		return userDAO.queryTotal(params);
	}

	@Override
	public DmUser queryUserByUserName(String username)
	{
		String key = Constants.USER_KEY + username;
		// 1.从缓存中查询数据
		if (redisUtil.hasKey(key))
		{
			return redisUtil.getCacheObject(key);
		}
		// 2.缓存中不存在，则查询数据库
		DmUser user = userDAO.queryUserByUserName(username);
		/*
		查询数据库中不存在的数据，会有缓存穿透问题，
		解决办法：
		1.将空对象也放入缓存，设置过期时间。
		之后再查询此username不存在的数据时，从缓存返回，减少数据库访问
		2.布隆过滤器
		*/
		if (user != null)
		{
			// 3.将对象存入缓存
			redisUtil.setCacheObject(key, user);
		} else
		{
			// 将空对象存入缓存
			redisUtil.setCacheObject(key, null, 600, TimeUnit.SECONDS);
		}
		return user;
	}

	@Override
	public void register(DmUser user)
	{
		DmUser userDB = userDAO.queryUserByUserName(user.getUsername());
		if (userDB != null)
		{
			throw new RuntimeException("用户已存在！");
		}
		// TODO 密码应该前端也加密
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 处理创建人，创建时间字段
		Date serverDate = DateUtil.getServerDate();
		user.setCreateTime(serverDate);
		user.setCreateUser(user.getUsername());
		userDAO.save(user);
	}

	@Override
	public void updateUser(DmUser user)
	{
		// TODO 修改为更新有值字段
		// if (user.getPassword() != null)
		// {
		// 	user.setPassword(passwordEncoder.encode(user.getPassword()));
		// }
		// 1.更新数据库
		user.setModifyTime(DateUtil.getServerDate());
		userDAO.updateById(user);
		// 2.更新缓存
		String key = Constants.USER_KEY + user.getUsername();
		redisUtil.setCacheObject(key, user);
		// TODO 依赖问题 3.更新token
	}

	@Override
	public void deleteUserByLogic(int id)
	{
		DmUser user = new DmUser();
		user.setId(id);
		user.setStatus(Constants.STATUS_DELETE);
		userDAO.updateById(user);
	}

	@Override
	public void deleteUserById(int id)
	{
		userDAO.deleteById(id);
	}
}
