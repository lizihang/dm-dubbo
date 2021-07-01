package com.dm.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.constant.Constants;
import com.dm.system.dao.RoleDAO;
import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
import com.dm.system.service.RoleService;
import org.apache.dubbo.apidocs.annotations.ApiModule;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
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
	public Page<DmRole> queryPage(DmRoleQueryParams params)
	{
		Page<DmRole> page = new Page<>(params.getPageNum(), params.getPageSize());
		QueryWrapper<DmRole> wrapper = buildQueryWrapper(params);
		return roleDAO.selectPage(page, wrapper);
	}

	@Override
	public DmRole queryRole(DmRoleQueryParams params)
	{
		QueryWrapper<DmRole> wrapper = buildQueryWrapper(params);
		return roleDAO.selectOne(wrapper);
	}

	@Override
	public void addRole(DmRole role)
	{
		roleDAO.insert(role);
	}

	@Override
	public void updateRole(DmRole role)
	{
		roleDAO.updateById(role);
	}

	@Override
	public void deleteRoleByLogic(int id)
	{
		DmRole role = new DmRole();
		role.setId(id);
		role.setStatus(Constants.STATUS_DELETE);
		roleDAO.updateById(role);
	}

	@Override
	public void deleteRole(int id)
	{
		roleDAO.deleteById(id);
	}

	/**
	 * 处理查询wrapper
	 * @param params
	 * @return
	 */
	private QueryWrapper<DmRole> buildQueryWrapper(DmRoleQueryParams params)
	{
		QueryWrapper<DmRole> wrapper = new QueryWrapper<>();
		if (params.getUsername() != null)
		{
			wrapper.like("username", params.getUsername());
		}
		if (params.getRoleName() != null)
		{
			wrapper.like("role_name", params.getRoleName());
		}
		if (params.getStatus() != null)
		{
			wrapper.eq("status", params.getStatus());
		}
		return wrapper;
	}
}
