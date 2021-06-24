package com.dm.system.po;

import com.dm.po.BasePO;

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
 * <p>创建日期：2021年06月23日 15:57</p>
 * <p>类全名：com.dm.system.po.Dict</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Dict extends BasePO
{
	private static final long           serialVersionUID = -678935380140833001L;
	/**id*/
	private              String         dictId;
	private              String         dictName;
	private              String         status;
	private              String         remark;
	private              List<DictInfo> dictInfoList;

	public String getDictId()
	{
		return dictId;
	}

	public void setDictId(String dictId)
	{
		this.dictId = dictId;
	}

	public String getDictName()
	{
		return dictName;
	}

	public void setDictName(String dictName)
	{
		this.dictName = dictName;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public List<DictInfo> getDictInfoList()
	{
		return dictInfoList;
	}

	public void setDictInfoList(List<DictInfo> dictInfoList)
	{
		this.dictInfoList = dictInfoList;
	}

	@Override
	public String toString()
	{
		return "Dict{" + "dictId='" + dictId + '\'' + ", dictName='" + dictName + '\'' + ", status='" + status + '\'' + ", remark='" + remark + '\'' + ", dictInfoList=" + dictInfoList + "} " + super.toString();
	}
}
