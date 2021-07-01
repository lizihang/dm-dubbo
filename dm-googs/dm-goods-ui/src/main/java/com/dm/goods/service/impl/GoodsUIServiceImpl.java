package com.dm.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;
import com.dm.goods.service.GoodsService;
import com.dm.goods.service.GoodsUIService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月24日 10:08</p>
 * <p>类全名：com.dm.goods.service.impl.GoodsUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class GoodsUIServiceImpl implements GoodsUIService
{
	@DubboReference
	GoodsService goodsService;

	@Override
	public Page<Goods> queryPageUI(GoodsQueryParams params)
	{
		return goodsService.queryPage(params);
	}

	@Override
	public Goods queryGoodsUI(GoodsQueryParams params)
	{
		return goodsService.queryGoods(params);
	}

	@Override
	public void addGoodsUI(Goods goods)
	{
		goodsService.addGoods(goods);
	}

	@Override
	public void updateGoodsUI(Goods goods)
	{
		goodsService.updateGoods(goods);
	}

	@Override
	public void deleteGoodsUI(int id)
	{
		goodsService.deleteGoods(id);
	}
}
