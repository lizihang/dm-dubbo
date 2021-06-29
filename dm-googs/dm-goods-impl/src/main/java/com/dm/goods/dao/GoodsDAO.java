package com.dm.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.goods.param.GoodsQueryParams;
import com.dm.goods.po.Goods;
import org.apache.ibatis.annotations.Mapper;

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
 * <p>创建日期：2021年06月24日 10:11</p>
 * <p>类全名：com.dm.goods.dao.GoodsDAO</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Mapper
public interface GoodsDAO extends BaseMapper<Goods>
{
	List<Goods> queryGoodsList(GoodsQueryParams params);

	int queryTotal(GoodsQueryParams params);
}
