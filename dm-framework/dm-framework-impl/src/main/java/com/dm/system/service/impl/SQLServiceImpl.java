package com.dm.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.system.dao.SQLDao;
import com.dm.system.po.SQLUser;
import com.dm.system.service.SQLService;
import org.springframework.stereotype.Service;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月16日 9:36</p>
 * <p>类全名：com.dm.system.service.impl.SQLServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SQLServiceImpl extends ServiceImpl<SQLDao,SQLUser> implements SQLService
{
}
