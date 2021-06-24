package com.dm.system.service.impl;

import com.dm.system.param.DmDictQueryParams;
import com.dm.system.po.Dict;
import com.dm.system.po.DictInfo;
import com.dm.system.service.SysService;
import com.dm.system.service.SysUIService;
import com.dm.system.vo.Menus;
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
 * <p>创建日期：2021年06月23日 15:54</p>
 * <p>类全名：com.dm.system.service.impl.SysUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysUIServiceImpl implements SysUIService
{
	@DubboReference
	SysService sysService;

	@Override
	public List<Menus> getMenusUI()
	{
		return sysService.getMenus();
	}

	@Override
	public List<Dict> queryDictListUI(DmDictQueryParams params)
	{
		return sysService.queryDictList(params);
	}

	@Override
	public Object queryDictTotalUI(DmDictQueryParams params)
	{
		return sysService.queryDictTotal(params);
	}

	@Override
	public List<DictInfo> queryDictInfoUI(String dictId)
	{
		return sysService.queryDictInfo(dictId);
	}

	@Override
	public void addDictUI(Dict dict)
	{
		sysService.addDict(dict);
	}

	@Override
	public void updateDictUI(Dict dict)
	{
		sysService.updateDict(dict);
	}

	@Override
	public void deleteDictUI(String dictId)
	{
		sysService.deleteDict(dictId);
	}
}
