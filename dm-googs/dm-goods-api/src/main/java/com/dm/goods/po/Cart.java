package com.dm.goods.po;

import com.dm.po.BasePO;
/**
 * <p>标题：购物车</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月29日 16:32</p>
 * <p>类全名：com.dm.goods.po.Cart</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Cart extends BasePO
{
	private static final long    serialVersionUID = -5384085947865558045L;
	/** id */
	private              int     id;
	/** 用户id */
	private              int     userId;
	/** 商品id */
	private              int     goodsId;
	/** 数量 */
	private              int     quantity;
	/** 是否勾选 */
	private              boolean check;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getGoodsId()
	{
		return goodsId;
	}

	public void setGoodsId(int goodsId)
	{
		this.goodsId = goodsId;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public boolean isCheck()
	{
		return check;
	}

	public void setCheck(boolean check)
	{
		this.check = check;
	}

	@Override
	public String toString()
	{
		return "Cart{" + "id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", quantity=" + quantity + ", check=" + check + "} " + super.toString();
	}
}

