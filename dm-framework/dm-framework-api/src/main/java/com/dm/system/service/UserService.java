package com.dm.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.NamedBean;
/**
 * <p>标题：用户service接口</p>
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
public interface UserService extends BeanNameAware, NamedBean
{
	/**
	 * 查询用户分页数据
	 * @return 用户列表
	 */
	Page<DmUser> queryPage(DmUserQueryParams params);

	/**
	 * 查询用户信息
	 * @return 用户对象
	 */
	DmUser queryUser(DmUserQueryParams params);

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	DmUser queryUserByUsername(String username);

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
	 * 物理删除
	 * @param id id
	 */
	void deleteUser(int id);
}
