package com.dm.goods.service.impl;

import com.dm.goods.po.Cart;
import com.dm.goods.service.CartService;
import com.dm.goods.service.CartUIService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

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
 * <p>创建日期：2021年06月29日 17:04</p>
 * <p>类全名：com.dm.goods.service.impl.CartUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class CartUIServiceImpl implements CartUIService
{
	@DubboReference
	CartService cartService;

	@Override
	public List<Cart> queryCartListUI(int userId)
	{
		return cartService.queryCartList(userId);
	}

	@Override
	public void addCartUI(Cart cart)
	{
		cartService.addCart(cart);
	}

	@Override
	public void updateCartUI(Cart cart)
	{
		cartService.updateCart(cart);
	}

	@Override
	public void deleteCartUI(int cartId)
	{
		cartService.deleteCart(cartId);
	}
}
