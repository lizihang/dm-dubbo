package com.dm.system.service.impl;

import com.dm.exception.DmException;
import com.dm.system.dao.SysDAO;
import com.dm.system.param.DmDictQueryParams;
import com.dm.system.po.Dict;
import com.dm.system.po.DictInfo;
import com.dm.system.service.SysService;
import com.dm.system.vo.Menus;
import com.dm.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * <p>创建日期：2021年06月23日 17:52</p>
 * <p>类全名：com.dm.system.service.impl.SysServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
@Transactional
public class SysServiceImpl implements SysService
{
	@Resource
	SysDAO sysDAO;

	@Override
	public List<Menus> getMenus()
	{
		return sysDAO.getMenus();
	}

	@Override
	public List<Dict> queryDictList(DmDictQueryParams params)
	{
		PageHelper.startPage(params.getPageNum(), params.getPageSize());
		return sysDAO.queryDictList(params);
	}

	@Override
	public Object queryDictTotal(DmDictQueryParams params)
	{
		return sysDAO.queryDictTotal(params);
	}

	@Override
	public List<DictInfo> queryDictInfo(String dictId)
	{
		if (StrUtil.isEmpty(dictId))
		{
			throw new DmException("字典id不能为空！");
		}
		return sysDAO.queryDictInfo(dictId);
	}

	@Override
	public void addDict(Dict dict)
	{
		sysDAO.addDict(dict);
	}

	@Override
	public void updateDict(Dict dict)
	{
		sysDAO.updateDict(dict);
	}

	@Override
	public void deleteDict(String dictId)
	{
		// 1.先删除子表
		sysDAO.deleteDictInfo(dictId);
		// 2.删除主表
		sysDAO.deleteDict(dictId);
	}
}
