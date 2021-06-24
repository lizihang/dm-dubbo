package com.dm.system.handler;

import com.dm.constant.Constants;
import com.dm.system.util.ServletUtil;
import com.dm.system.vo.LoginUser;
import com.dm.util.RedisUtil;
import com.dm.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2020年12月09日 15:39</p>
 * <p>类全名：com.dm.system.handler.LoginSuccessHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler
{
	@Resource
	RedisUtil redisUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
		// TODO 登录成功 记录日志
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		redisUtil.setCacheObject(Constants.LOGIN_USER_KEY + loginUser.getUsername(), loginUser);
		Result result = Result.success("登录成功", loginUser);
		ServletUtil.render(response, result);
	}
}
