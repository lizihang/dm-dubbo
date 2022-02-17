package com.dm.constant;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月22日 9:56</p>
 * <p>类全名：com.dm.constant.Constants</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Constants
{
	public static final String   STATUS_CREATE         = "10";
	public static final String   STATUS_NORMAL         = "20";
	public static final String   STATUS_DISABLE        = "30";
	public static final String   STATUS_DELETE         = "40";
	/***/
	public static final int      HTTP_STATUS_OK_VALUE  = 200;
	/** 用户 redis key */
	public static final String   USER_KEY              = "user:";
	/** 是否检查权限 */
	public static final boolean  IS_CHECK_PERMISSION   = false;
	/** 登录用户 redis key */
	public static final String   LOGIN_USER_KEY        = "login_user:";
	/** 请求中TOKEN名 */
	public static final String   TOKEN_HEADER          = "Authorization";
	/** 令牌前缀 注意后边带空格*/
	public static final String   TOKEN_PREFIX          = "Bearer ";
	/** 私钥 */
	public static final String   TOKEN_SECRET_KEY      = "dm_security";
	/** 过期时间 毫秒,设置默认1天的时间过期 */
	public static final long     TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000;
	// TODO 测试用
	// public static final long     TOKEN_EXPIRATION_TIME = 10 * 1000;
	/**==================== 错误码 ====================*/
	public static final String   ERR_CODE_00           = "当前登录用户不存在！请检查";
	/**==================== 不需要权限的 ====================*/
	public static final String[] AUTH_WHITELIST        = {
			// 登录相关
			"/system/getCodeImg", "/user/register",
			// swagger3相关
			"/swagger-ui/**", "/v3/**", "/swagger-resources/**"
			// swagger2相关
			// "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**"
			// 静态资源，头像
			, "/avatar/**"
			// 测试用
			, "/user/queryUserList", "/goods/**" };
}
