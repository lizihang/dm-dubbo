package com.dm.system.handler;

import com.dm.system.util.ServletUtil;
import com.dm.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

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
 * <p>创建日期：2020年12月16日 15:23</p>
 * <p>类全名：com.dm.system.handler.UserAuthAccessDeniedHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler
{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException
	{
		Result result = Result.error(HttpStatus.FORBIDDEN.value(), "用户没有权限！");
		ServletUtil.render(response, result);
	}
}
