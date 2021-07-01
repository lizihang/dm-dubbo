package com.dm.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;
/**
 * <p>标题：商品service</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月24日 10:00</p>
 * <p>类全名：com.dm.goods.service.GoodsService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface GoodsService
{
	/**
	 * 查询商品分页信息
	 * @param params
	 * @return
	 */
	Page<Goods> queryPage(GoodsQueryParams params);

	/**
	 * 查询商品信息
	 * @param params
	 * @return
	 */
	Goods queryGoods(GoodsQueryParams params);

	/**
	 * 新增商品信息
	 * @param goods
	 */
	void addGoods(Goods goods);

	/**
	 * 修改商品信息
	 * @param goods
	 */
	void updateGoods(Goods goods);

	/**
	 * 删除商品信息
	 * @param id
	 */
	void deleteGoods(int id);
}
