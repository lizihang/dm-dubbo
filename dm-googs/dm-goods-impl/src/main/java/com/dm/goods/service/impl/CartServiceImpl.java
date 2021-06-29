package com.dm.goods.service.impl;

import com.dm.goods.dao.CartDAO;
import com.dm.goods.po.Cart;
import com.dm.goods.service.CartService;
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
 * <p>创建日期：2021年06月29日 17:12</p>
 * <p>类全名：com.dm.goods.service.impl.CartServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@DubboService
public class CartServiceImpl implements CartService
{
	@Resource
	CartDAO cartDAO;

	@Override
	public List<Cart> queryCartList(int userId)
	{
		return null;
	}

	@Override
	public void addCart(Cart cart)
	{
	}

	@Override
	public void updateCart(Cart cart)
	{
	}

	@Override
	public void deleteCart(int cartId)
	{
	}
}
