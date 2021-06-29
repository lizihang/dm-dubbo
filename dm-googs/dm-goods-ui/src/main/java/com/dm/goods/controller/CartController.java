package com.dm.goods.controller;

import com.dm.goods.po.Cart;
import com.dm.goods.service.CartUIService;
import com.dm.system.annotation.DmLog;
import com.dm.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月29日 17:01</p>
 * <p>类全名：com.dm.goods.controller.CartController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("cart")
public class CartController
{
	@Resource
	CartUIService cartUIService;

	/**
	 * 根据userId查询购物车信息
	 * @param userId user_id
	 * @return result
	 */
	@DmLog
	@GetMapping("queryCartList")
	public Result queryList(int userId)
	{
		Map<String,Object> data = new HashMap<>();
		data.put("total", cartUIService.queryCartListUI(userId));
		return Result.success("查询购物车成功", data);
	}

	/**
	 * 新增购物车信息
	 * @param cart
	 * @return
	 */
	@DmLog
	@PostMapping("addCart")
	public Result addCart(Cart cart)
	{
		cartUIService.addCartUI(cart);
		return Result.success("新增购物车信息成功");
	}

	/**
	 * 更新购物车信息
	 * @param cart
	 * @return
	 */
	@DmLog
	@PostMapping("updateCart")
	public Result updateCart(Cart cart)
	{
		cartUIService.updateCartUI(cart);
		return Result.success("新购物车信息成功");
	}

	/**
	 * 根据userId删除购物车信息
	 * @param userId
	 * @return
	 */
	@DmLog
	@GetMapping("deleteCart")
	public Result deleteCart(int userId)
	{
		cartUIService.deleteCartUI(userId);
		return Result.success("删除购物车信息成功");
	}
}
