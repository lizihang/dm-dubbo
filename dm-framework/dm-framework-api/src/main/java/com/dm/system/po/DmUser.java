package com.dm.system.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dm.po.BasePO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 19:07</p>
 * <p>类全名：com.dm.system.po.DmUser</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude // 字段值为null时也序列化，加在类上所有字段生效，也可以单独加字段上
public class DmUser extends BasePO
{
	private static final long    serialVersionUID = -8121407232554708400L;
	/** id */
	@TableId(type = IdType.AUTO)
	private              Integer id;
	/** 用户名 */
	private              String  username;
	/** 密码 */
	@JSONField(serialize = false)
	@JsonIgnore // Redisson配置了JsonJacksonCodec序列化方式，序列化时使用此注解忽略该字段
	private              String  password;
	/** 昵称 */
	private              String  nickname;
	/** 邮箱 */
	private              String  email;
	/** 手机号 */
	private              String  phone;
	/** 性别 */
	private              Integer gender;
	/** 头像 */
	private              String  avatar;
	/** 状态 */
	private              String  status;

	/*
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public int getGender()
	{
		return gender;
	}

	public void setGender(int gender)
	{
		this.gender = gender;
	}

	public String getAvatar()
	{
		return avatar;
	}

	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "DmUser{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", nickname='" + nickname + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", gender=" + gender + ", avatar='" + avatar + '\'' + ", status='" + status + '\'' + "} " + super.toString();
	}
	*/
}
