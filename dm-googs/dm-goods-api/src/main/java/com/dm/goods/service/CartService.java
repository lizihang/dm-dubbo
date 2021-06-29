package com.dm.goods.service;

import com.dm.goods.po.Cart;

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
 * <p>创建日期：2021年06月29日 17:05</p>
 * <p>类全名：com.dm.goods.service.CartService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface CartService
{
	List<Cart> queryCartList(int userId);

	void addCart(Cart cart);

	void updateCart(Cart cart);

	void deleteCart(int cartId);
}
