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
 * <p>创建日期：2021年06月29日 17:03</p>
 * <p>类全名：com.dm.goods.service.CartUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface CartUIService
{
	/**
	 * 根据用户id查询购物车
	 * @param userId user_id
	 * @return result
	 */
	List<Cart> queryCartListUI(int userId);

	/**
	 * 新增购物车信息
	 * @param cart
	 */
	void addCartUI(Cart cart);

	/**
	 * 修改购物车信息
	 * @param cart
	 */
	void updateCartUI(Cart cart);

	/**
	 * 删除购物车信息
	 * @param cartId
	 */
	void deleteCartUI(int cartId);
}
