package com.dm.system.service;

import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;

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
 * <p>创建日期：2021年06月21日 19:13</p>
 * <p>类全名：com.dm.system.service.UserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface UserService
{
	/**
	 * 查询所有用户
	 * @return 用户列表
	 */
	List<DmUser> queryList(DmUserQueryParams params);

	/**
	 * 查询总数量
	 * @param params
	 * @return
	 */
	int queryTotal(DmUserQueryParams params);

	/**
	 * 根据用户名查找
	 * @return 用户对象
	 */
	DmUser queryUserByUserName(String username);

	/**
	 * 用户注册
	 * @param user 用户
	 */
	void register(DmUser user);

	/**
	 * 用户修改
	 * @param user 用户
	 */
	void updateUser(DmUser user);

	/**
	 * 逻辑删除
	 * @param id
	 */
	void deleteUserByLogic(int id);

	/**
	 * 根据用户id删除用户
	 * @param id id
	 */
	void deleteUserById(int id);
}
