package com.dm.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.system.po.SQLUser;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>标题：SQL优化DAO</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月16日 9:24</p>
 * <p>类全名：com.dm.system.dao.SQLDao</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Mapper
public interface SQLDao extends BaseMapper<SQLUser>
{

}
