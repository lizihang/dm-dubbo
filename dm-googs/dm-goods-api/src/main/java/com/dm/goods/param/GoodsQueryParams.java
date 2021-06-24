package com.dm.goods.param;

import com.dm.vo.QueryParams;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月24日 10:01</p>
 * <p>类全名：com.dm.goods.param.GoodsQueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class GoodsQueryParams extends QueryParams
{
	private static final long   serialVersionUID = 5707551209090509737L;
	/** 商品名称 */
	private              String goodsName;

	public String getGoodsName()
	{
		return goodsName;
	}

	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}

	@Override
	public String toString()
	{
		return "GoodsQueryParams{" + "goodsName='" + goodsName + '\'' + "} " + super.toString();
	}
}
