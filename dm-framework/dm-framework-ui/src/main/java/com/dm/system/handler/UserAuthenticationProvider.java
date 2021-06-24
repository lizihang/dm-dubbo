package com.dm.system.handler;

import com.dm.system.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
/**
 * <p>标题：自定义登录验证类</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：暂时不用，以后扩展复杂验证时再使用
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2020年12月16日 16:22</p>
 * <p>类全名：com.dm.system.handler.UserAuthenticationProvider</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @Component
public class UserAuthenticationProvider implements AuthenticationProvider
{
	@Resource(name = UserDetailsServiceImpl.BeanName)
	UserDetailsService userDetailsService;

	@Resource
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		/*
		// 获取表单输入中返回的用户名
		String userName = (String) authentication.getPrincipal();
		// 获取表单中输入的密码
		String password = (String) authentication.getCredentials();
		// 查询用户是否存在
		LoginUser loginUser = (LoginUser) userDetailsService.loadUserByUsername(userName);
		if (loginUser == null)
		{
			throw new UsernameNotFoundException("用户名不存在");
		}
		// 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
		if (!passwordEncoder.matches(password, loginUser.getPassword()))
		{
			throw new BadCredentialsException("密码不正确");
		}
		// 还可以加一些其他信息的判断，比如用户账号已停用等判断
		// if (loginUser.getStatus().equals("PROHIBIT"))
		// {
		// 	throw new LockedException("该用户已被冻结");
		// }
		// 进行登录
		return new UsernamePasswordAuthenticationToken(loginUser, password);
		 */
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return true;
	}
}
