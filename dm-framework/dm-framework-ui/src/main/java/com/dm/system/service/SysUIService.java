package com.dm.system.service;

import com.dm.system.param.DmDictQueryParams;
import com.dm.system.po.Dict;
import com.dm.system.po.DictInfo;
import com.dm.system.vo.Menus;

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
 * <p>创建日期：2021年06月23日 15:53</p>
 * <p>类全名：com.dm.system.service.SysUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysUIService
{
	List<Menus> getMenusUI();

	List<Dict> queryDictListUI(DmDictQueryParams params);

	Object queryDictTotalUI(DmDictQueryParams params);

	List<DictInfo> queryDictInfoUI(String dictId);

	void addDictUI(Dict dict);

	void updateDictUI(Dict dict);

	void deleteDictUI(String dictId);
}
