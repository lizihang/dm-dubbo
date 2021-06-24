package com.dm.goods.service.impl;

import com.dm.goods.dao.GoodsDAO;
import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;
import com.dm.goods.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboService;

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
 * <p>创建日期：2021年06月24日 10:04</p>
 * <p>类全名：com.dm.goods.service.impl.GoodsServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@DubboService
public class GoodsServiceImpl implements GoodsService
{
	@Resource
	GoodsDAO    goodsDAO;

	@Override
	public List<Goods> queryGoodsList(GoodsQueryParams params)
	{
		return goodsDAO.queryGoodsList(params);
	}

	@Override
	public int queryTotal(GoodsQueryParams params)
	{
		return goodsDAO.queryTotal(params);
	}
}
