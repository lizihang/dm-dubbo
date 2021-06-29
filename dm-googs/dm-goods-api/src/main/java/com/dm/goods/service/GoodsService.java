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
 * <p>创建日期：2021年06月24日 10:00</p>
 * <p>类全名：com.dm.goods.service.GoodsService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface GoodsService
{
	/**
	 * 查询商品列表
	 * @param params
	 * @return
	 */
	List<Goods> queryGoodsList(GoodsQueryParams params);

	/**
	 * 查询商品表
	 * @param params
	 * @return
	 */
	int queryTotal(GoodsQueryParams params);

	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	Goods queryGoodsById(int id);

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
	 * 根据id删除商品
	 * @param id
	 */
	void deleteGoodsById(int id);
}
