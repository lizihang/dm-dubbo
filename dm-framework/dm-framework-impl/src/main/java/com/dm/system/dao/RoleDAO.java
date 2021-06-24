package com.dm.system.dao;

import com.dm.system.param.DmRoleQueryParams;
import com.dm.system.po.DmRole;
import org.apache.ibatis.annotations.Mapper;

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
 * <p>创建日期：2021年06月08日 19:18</p>
 * <p>类全名：com.dm.system.dao.RoleDAO</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Mapper
public interface RoleDAO
{
	List<DmRole> queryList(DmRoleQueryParams params);

	int queryTotal(DmRoleQueryParams params);

	void save(DmRole role);

	void update(DmRole role);

	void deleteById(int id);
}
