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
 * <p>创建日期：2021年06月23日 17:51</p>
 * <p>类全名：com.dm.system.service.RoleService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface RoleService
{
	/**
	 * 查询角色分页数据
	 * @param params
	 * @return
	 */
	Page<DmRole> queryPage(DmRoleQueryParams params);

	/**
	 * 查询角色信息
	 * @return 用户对象
	 */
	DmRole queryRole(DmRoleQueryParams params);

	/**
	 * 新增角色
	 * @param role 角色
	 */
	void addRole(DmRole role);

	/**
	 * 更新角色
	 * @param role 角色
	 */
	void updateRole(DmRole role);

	/**
	 * 逻辑删除
	 * @param id
	 */
	void deleteRoleByLogic(int id);

	/**
	 * 根据用户id删除用户
	 * @param id id
	 */
	void deleteRole(int id);
}
