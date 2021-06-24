package com.dm.system.po;

import com.dm.po.BasePO;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 17:50</p>
 * <p>类全名：com.dm.system.po.DmRole</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class DmRole extends BasePO
{
	private static final long   serialVersionUID = 4668928820778562291L;
	/** id */
	private              int    id;
	/** 角色编码 */
	private              String roleCode;
	/** 角色名称 */
	private              String roleName;
	/** 状态 */
	private              String status;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "DmRole{" + "id=" + id + ", roleCode='" + roleCode + '\'' + ", roleName='" + roleName + '\'' + ", status='" + status + '\'' + "} " + super.toString();
	}
}
