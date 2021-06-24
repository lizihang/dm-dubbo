package com.dm.goods.service;

import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;

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
 * <p>创建日期：2021年06月24日 10:07</p>
 * <p>类全名：com.dm.goods.service.GoodsUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface GoodsUIService
{
	/**
	 * 查询商品列表
	 * @param params
	 * @return
	 */
	List<Goods> queryGoodsListUI(GoodsQueryParams params);

	/**
	 * 查询商品表
	 * @param params
	 * @return
	 */
	int queryTotalUI(GoodsQueryParams params);
}
