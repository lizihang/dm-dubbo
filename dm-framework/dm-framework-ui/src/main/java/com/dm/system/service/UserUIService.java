package com.dm.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
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
 * <p>类全名：com.dm.com.dm.system.service.UserUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface UserUIService
{
	/**
	 * 查询用户分页数据
	 * @return 用户分页数据
	 */
	Page<DmUser> queryPageUI(DmUserQueryParams params);

	/**
	 * 查询用户信息
	 * @return 用户对象
	 */
	DmUser queryUserUI(DmUserQueryParams params);

	/**
	 * 根据用户名查询用户信息
	 * @return
	 */
	DmUser queryUserByUsernameUI(String username);

	/**
	 * 用户注册
	 * @param user 用户
	 */
	void registerUI(DmUser user);

	/**
	 * 用户修改
	 * @param user 用户
	 */
	void updateUserUI(DmUser user);

	/**
	 * 逻辑删除
	 * @param id
	 */
	void deleteUserByLogicUI(int id);

	/**
	 * 根据用户id删除用户
	 * @param id id
	 */
	void deleteUserUI(int id);
}
