package com.dm.system.service.impl;

import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
import com.dm.system.service.RoleService;
import com.dm.system.service.RoleUIService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

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
 * <p>创建日期：2021年06月23日 17:55</p>
 * <p>类全名：com.dm.system.service.impl.RoleUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class RoleUIServiceImpl implements RoleUIService
{
	@DubboReference
	RoleService roleService;

	@Override
	public List<DmRole> queryListUI(DmRoleQueryParams params)
	{
		return roleService.queryList(params);
	}

	@Override
	public int queryTotalUI(DmRoleQueryParams params)
	{
		return roleService.queryTotal(params);
	}

	@Override
	public void addRoleUI(DmRole role)
	{
		roleService.addRole(role);
	}

	@Override
	public void updateRoleUI(DmRole role)
	{
		roleService.updateRole(role);
	}

	@Override
	public void deleteRoleByLogicUI(int id)
	{
		roleService.deleteRoleByLogic(id);
	}

	@Override
	public void deleteRoleByIdUI(int id)
	{
		roleService.deleteRoleById(id);
	}
}
