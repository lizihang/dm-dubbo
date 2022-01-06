package com.dm.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.constant.Constants;
import com.dm.system.dao.UserDAO;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import com.dm.system.service.UserService;
import com.dm.util.RedisUtil;
import org.apache.dubbo.apidocs.annotations.ApiDoc;
import org.apache.dubbo.apidocs.annotations.ApiModule;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * <p>标题：用户service实现类</p>
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
	// logger
	static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	UserDAO   userDAO;
	@Resource
	RedisUtil redisUtil;
	// beanName
	private String beanName;

	@ApiDoc(value = "查询用户分页数据", description = "查询用户分页数据", responseClassDescription = "用户分页数据")
	@Override
	public Page<DmUser> queryPage(DmUserQueryParams params)
	{
		Page<DmUser> page = new Page<>(params.getPageNum(), params.getPageSize());
		QueryWrapper<DmUser> wrapper = buildQueryWrapper(params);
		Page<DmUser> dmUserPage = userDAO.selectPage(page, wrapper);
		dmUserPage.getRecords().forEach(user -> user.setPassword(null));
		return dmUserPage;
	}

	@ApiDoc(value = "查询单个用户数据", description = "查询单个用户数据", responseClassDescription = "用户数据")
	@Override
	public DmUser queryUser(DmUserQueryParams params)
	{
		QueryWrapper<DmUser> wrapper = buildQueryWrapper(params);
		return userDAO.selectOne(wrapper);
	}

	@ApiDoc(value = "根据用户名查询用户信息", description = "根据用户名查询用户信息", responseClassDescription = "用户数据")
	@Override
	public DmUser queryUserByUsername(String username)
	{
		QueryWrapper<DmUser> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		return userDAO.selectOne(wrapper);
	}

	@ApiDoc(value = "注册用户", description = "注册用户", responseClassDescription = "注册用户")
	@Override
	public void register(DmUser user)
	{
		QueryWrapper<DmUser> wrapper = new QueryWrapper<>();
		wrapper.eq("username", user.getUsername());
		DmUser userDB = userDAO.selectOne(wrapper);
		if (userDB != null)
		{
			throw new RuntimeException("用户已存在！");
		}
		userDAO.insert(user);
	}

	@ApiDoc(value = "更新用户", description = "更新用户", responseClassDescription = "更新用户")
	@Override
	@Transactional
	public void updateUser(DmUser user)
	{
		// 1.更新数据库
		int i = userDAO.updateById(user);
		// 2.更新缓存
		String key = Constants.USER_KEY + user.getUsername();
		redisUtil.setCacheObject(key, user);
		// TODO 依赖问题 3.更新token
		if (user.getId() == 12)
		{
			throw new RuntimeException("test");
		}
	}

	@ApiDoc(value = "逻辑删除", description = "逻辑删除", responseClassDescription = "逻辑删除")
	@Override
	public void deleteUserByLogic(int id)
	{
		DmUser user = new DmUser();
		user.setId(id);
		user.setStatus(Constants.STATUS_DELETE);
		userDAO.updateById(user);
	}

	@ApiDoc(value = "物理删除", description = "物理删除", responseClassDescription = "物理删除")
	@Override
	public void deleteUser(int id)
	{
		userDAO.deleteById(id);
	}

	/**
	 * 处理查询wrapper
	 * @param params
	 * @return
	 */
	private QueryWrapper<DmUser> buildQueryWrapper(DmUserQueryParams params)
	{
		QueryWrapper<DmUser> wrapper = new QueryWrapper<>();
		if (params.getUsername() != null)
		{
			wrapper.like("username", params.getUsername());
		}
		if (params.getNickname() != null)
		{
			wrapper.like("nickname", params.getNickname());
		}
		if (params.getEmail() != null)
		{
			wrapper.like("email", params.getEmail());
		}
		if (params.getStatus() != null)
		{
			wrapper.eq("status", params.getStatus());
		}
		return wrapper;
	}

	@Override
	public void setBeanName(@NonNull String name)
	{
		logger.info("========== BeanNameAware ==========");
		this.beanName = name;
		logger.info("beanName:{}", this.beanName);
		logger.info("========== BeanNameAware ==========");
	}


	@Override
	@NonNull
	public String getBeanName()
	{
		return this.beanName;
	}
}
