package com.dm.vo;

import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 17:34</p>
 * <p>类全名：com.dm.vo.QueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class QueryParams implements Serializable
{
	private static final long serialVersionUID = -8940095516485474541L;
	/** 分页查询，查询页码 */
	private              int  pageNum;
	/** 分页查询，每页数量 */
	private              int  pageSize;

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	@Override
	public String toString()
	{
		return "QueryParams{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + '}';
	}
}
