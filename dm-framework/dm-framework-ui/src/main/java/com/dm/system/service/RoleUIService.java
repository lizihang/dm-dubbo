package com.dm.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 17:55</p>
 * <p>类全名：com.dm.system.service.RoleUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface RoleUIService
{
	/**
	 * 查询所有角色
	 * @return 用户列表
	 */
	Page<DmRole> queryPageUI(DmRoleQueryParams params);

	/**
	 * 查询角色信息
	 * @return 用户对象
	 */
	DmRole queryRoleUI(DmRoleQueryParams params);

	/**
	 * 新增角色
	 * @param role 角色
	 */
	void addRoleUI(DmRole role);

	/**
	 * 更新角色
	 * @param role 角色
	 */
	void updateRoleUI(DmRole role);

	/**
	 * 逻辑删除
	 * @param id
	 */
	void deleteRoleByLogicUI(int id);

	/**
	 * 根据用户id删除用户
	 * @param id id
	 */
	void deleteRoleUI(int id);
}
