package com.dm.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dm.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月16日 9:24</p>
 * <p>类全名：com.dm.system.po.SQLUser</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("user")
public class SQLUser extends BasePO
{
	private static final long    serialVersionUID = 2531482309557196684L;
	/** id */
	@TableId(type = IdType.AUTO)
	private              Integer id;
	/** 用户名 */
	private              String  username;
	/** 密码 */
	private              String  password;
	/** 昵称 */
	private              String  nickname;
	/** 邮箱 */
	private              String  email;
	private              String  txt1;
	private              String  txt2;
	private              String  txt3;
	private              String  txt4;
	private              String  txt5;
	private              String  txt6;
	private              String  txt7;
	private              String  txt8;
	private              String  txt9;
	private              String  txt10;
	private              String  txt11;
	private              String  txt12;
	private              String  txt13;
	private              String  txt14;
	private              String  txt15;
	private              String  txt16;
	private              String  txt17;
	private              String  txt18;
	private              String  txt19;
	private              String  txt20;
}
