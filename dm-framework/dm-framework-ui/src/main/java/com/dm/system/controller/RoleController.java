package com.dm.system.controller;

import com.dm.system.annotation.DmLog;
import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
import com.dm.system.service.RoleUIService;
import com.dm.vo.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 17:54</p>
 * <p>类全名：com.dm.system.controller.RoleController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("role")
public class RoleController
{
	@Resource
	RoleUIService roleUIService;

	/**
	 * 查询所有角色列表
	 * @param params 查询参数
	 * @return result
	 */
	@DmLog
	@GetMapping("queryRoleList")
	@PreAuthorize("@ps.permission('role:query')")
	public Result queryList(DmRoleQueryParams params)
	{
		Map<String,Object> data = new HashMap<>();
		data.put("list", roleUIService.queryListUI(params));
		data.put("total", roleUIService.queryTotalUI(params));
		return Result.success("查询角色列表成功", data);
	}

	/**
	 * 新增角色
	 * @param role 角色
	 * @return result
	 */
	@DmLog
	@PostMapping("addRole")
	@PreAuthorize("@ps.permission('role:add')")
	public Result addRole(@RequestBody DmRole role)
	{
		roleUIService.addRoleUI(role);
		return Result.success("新增角色成功!");
	}

	/**
	 * 更新角色
	 * @param role 角色
	 * @return result
	 */
	@DmLog
	@PostMapping("updateRole")
	@PreAuthorize("@ps.permission('role:update')")
	public Result updateRole(@RequestBody DmRole role)
	{
		roleUIService.updateRoleUI(role);
		return Result.success("更新角色成功");
	}

	/**
	 * 逻辑删除
	 * @param id id
	 * @return result
	 */
	@DmLog
	@DeleteMapping("deleteRoleByLogic")
	@PreAuthorize("@ps.permission('role:delete')")
	public Result deleteRoleByLogic(int id)
	{
		roleUIService.deleteRoleByLogicUI(id);
		return Result.success("删除成功，角色id：" + id);
	}

	/**
	 * 彻底删除
	 * @param id
	 * @return
	 */
	@DmLog
	@DeleteMapping("deleteCompletely")
	@PreAuthorize("@ps.permission('user:delete')")
	public Result deleteCompletely(int id)
	{
		roleUIService.deleteRoleByIdUI(id);
		return Result.success("删除成功，角色id：" + id);
	}
}
