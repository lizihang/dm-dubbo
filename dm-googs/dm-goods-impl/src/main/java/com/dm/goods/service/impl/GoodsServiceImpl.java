package com.dm.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.goods.dao.GoodsDAO;
import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;
import com.dm.goods.service.GoodsService;
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
 * <p>创建日期：2021年06月24日 10:04</p>
 * <p>类全名：com.dm.goods.service.impl.GoodsServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@DubboService
public class GoodsServiceImpl implements GoodsService
{
	@Resource
	GoodsDAO goodsDAO;

	@Override
	public Page<Goods> queryPage(GoodsQueryParams params)
	{
		Page<Goods> page = new Page<>(params.getPageNum(), params.getPageSize());
		QueryWrapper<Goods> wrapper = buildQueryWrapper(params);
		return goodsDAO.selectPage(page, wrapper);
	}

	@Override
	public Goods queryGoods(GoodsQueryParams params)
	{
		QueryWrapper<Goods> wrapper = buildQueryWrapper(params);
		return goodsDAO.selectOne(wrapper);
	}

	@Override
	public void addGoods(Goods goods)
	{
		goodsDAO.insert(goods);
	}

	@Override
	public void updateGoods(Goods goods)
	{
		goodsDAO.updateById(goods);
	}

	@Override
	public void deleteGoods(int id)
	{
		goodsDAO.deleteById(id);
	}

	/**
	 * 处理查询wrapper
	 * @param params
	 * @return
	 */
	private QueryWrapper<Goods> buildQueryWrapper(GoodsQueryParams params)
	{
		QueryWrapper<Goods> wrapper = new QueryWrapper<>();
		if (params.getGoodsName() != null)
		{
			wrapper.like("goods_name", params.getGoodsName());
		}
		return wrapper;
	}
}
