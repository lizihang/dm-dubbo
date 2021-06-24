package com.dm.goods.po;

import com.dm.po.BasePO;

import java.math.BigDecimal;
/**
 * <p>标题：商品PO</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月24日 9:48</p>
 * <p>类全名：com.dm.goods.po.Goods</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Goods extends BasePO
{
	private static final long       serialVersionUID = 477629201103679691L;
	/** id */
	private              int        id;
	/** 商品名称 */
	private              String     goodsName;
	/** 价格 */
	private              BigDecimal price;
	/** 数量 */
	private              Integer    quantity;
	/** 描述 */
	private              String     remark;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getGoodsName()
	{
		return goodsName;
	}

	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String toString()
	{
		return "Goods{" + "id=" + id + ", goodsName='" + goodsName + '\'' + ", price=" + price + ", quantity=" + quantity + ", remark='" + remark + '\'' + "} " + super.toString();
	}
}
