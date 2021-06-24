package com.dm.system.handler;

import com.dm.constant.Constants;
import com.dm.system.util.JwtTokenUtil;
import com.dm.system.vo.LoginUser;
import com.dm.util.ObjectUtil;
import com.dm.util.RedisUtil;
import com.dm.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
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
 * <p>创建日期：2021年02月21日 14:59</p>
 * <p>类全名：com.dm.system.handler.JwtAuthenticationTokenFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	@Resource
	JwtTokenUtil jwtTokenUtil;
	@Resource
	RedisUtil    redisUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		String url = request.getRequestURL().toString();
		logger.info("请求地址url:" + url);
		LoginUser loginUser = jwtTokenUtil.getLoginUser(request);
		if (ObjectUtil.isNotEmpty(loginUser) && ObjectUtil.isEmpty(SecurityContextHolder.getContext().getAuthentication()))
		{
			// 如果token过期，清空redis中user缓存
			if (jwtTokenUtil.isTokenExpired(request))
			{
				redisUtil.deleteObject(Constants.LOGIN_USER_KEY + loginUser.getUsername());
				redisUtil.deleteObject(Constants.USER_KEY + loginUser.getUsername());
			} else
			{
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		String token = jwtTokenUtil.getToken(request);
		if (StrUtil.isNotEmpty(token) && ObjectUtil.isEmpty(loginUser))
		{
			// 当redis缓存中loginUser删除时，重新登录会查询user，此时如果redis中user存在，返回的是没有password的，会报登录失败
			// 临时解决：缓存中loginUser不存在时，user缓存也清空。
			// TODO loginUser和user缓存只留一个？
			String username = jwtTokenUtil.getUsernameFromToken(request);
			redisUtil.deleteObject(Constants.USER_KEY + username);
		}
		filterChain.doFilter(request, response);
	}
}
