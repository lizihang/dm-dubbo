package com.dm.goods.controller;

import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.service.GoodsUIService;
import com.dm.system.annotation.DmLog;
import com.dm.vo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * <p>创建日期：2021年06月24日 10:04</p>
 * <p>类全名：com.dm.goods.controller.GoodsController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("goods")
public class GoodsController
{
	@Resource
	GoodsUIService goodsUIService;

	/**
	 * 查询所有商品列表
	 * @param params 查询参数
	 * @return result
	 */
	@DmLog
	@GetMapping("queryGoodsList")
	public Result queryList(GoodsQueryParams params)
	{
		Map<String,Object> data = new HashMap<>();
		data.put("list", goodsUIService.queryGoodsListUI(params));
		data.put("total", goodsUIService.queryTotalUI(params));
		return Result.success("查询商品列表成功", data);
	}
}
