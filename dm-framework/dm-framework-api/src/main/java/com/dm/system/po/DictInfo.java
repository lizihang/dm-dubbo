package com.dm.system.po;

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
 * <p>创建日期：2021年06月23日 15:57</p>
 * <p>类全名：com.dm.system.po.DictInfo</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class DictInfo implements Serializable
{
	private static final long   serialVersionUID = -6739163662905539615L;
	private              int    id;
	private              String dictId;
	private              String dictKey;
	private              String dictValue;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDictId()
	{
		return dictId;
	}

	public void setDictId(String dictId)
	{
		this.dictId = dictId;
	}

	public String getDictKey()
	{
		return dictKey;
	}

	public void setDictKey(String dictKey)
	{
		this.dictKey = dictKey;
	}

	public String getDictValue()
	{
		return dictValue;
	}

	public void setDictValue(String dictValue)
	{
		this.dictValue = dictValue;
	}

	@Override
	public String toString()
	{
		return "DictInfo{" + "id=" + id + ", dictId='" + dictId + '\'' + ", dictKey='" + dictKey + '\'' + ", dictValue='" + dictValue + '\'' + '}';
	}
}
