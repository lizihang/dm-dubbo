package com.dm.system.service.impl;

import com.dm.constant.Constants;
import com.dm.system.dao.RoleDAO;
import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
import com.dm.system.service.RoleService;
import org.apache.dubbo.apidocs.annotations.ApiModule;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
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
 * <p>创建日期：2021年06月23日 17:51</p>
 * <p>类全名：com.dm.system.service.impl.RoleServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@ApiModule(value = "角色service", apiInterface = RoleService.class)
@DubboService
public class RoleServiceImpl implements RoleService
{
	@Resource
	RoleDAO roleDAO;

	@Override
	public List<DmRole> queryList(DmRoleQueryParams params)
	{
		// PageHelper 分页查询，放在查询前面
		// PageHelper.startPage(params.getPageNum(), params.getPageSize());
		return roleDAO.queryList(params);
	}

	@Override
	public int queryTotal(DmRoleQueryParams params)
	{
		return roleDAO.queryTotal(params);
	}

	@Override
	public void addRole(DmRole role)
	{
		roleDAO.save(role);
	}

	@Override
	public void updateRole(DmRole role)
	{
		roleDAO.update(role);
	}

	@Override
	public void deleteRoleByLogic(int id)
	{
		DmRole role = new DmRole();
		role.setId(id);
		role.setStatus(Constants.STATUS_DELETE);
		roleDAO.update(role);
	}

	@Override
	public void deleteRoleById(int id)
	{
		roleDAO.deleteById(id);
	}
}
