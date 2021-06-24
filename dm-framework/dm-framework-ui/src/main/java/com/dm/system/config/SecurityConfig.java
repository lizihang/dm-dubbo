package com.dm.system.config;

import com.dm.constant.Constants;
import com.dm.system.handler.*;
import com.dm.system.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月23日 15:37</p>
 * <p>类全名：com.dm.system.config.SecurityConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Resource(name = UserDetailsServiceImpl.BeanName)
	UserDetailsService userDetailsService;
	/**
	 * 自定义登录验证类
	 */
	// @Resource
	// private UserAuthenticationProvider          userAuthenticationProvider;
	/**
	 * 登录成功的处理
	 */
	@Resource
	private LoginSuccessHandler                 loginSuccessHandler;
	/**
	 * 登录失败的处理
	 */
	@Resource
	private LoginFailureHandler                 loginFailureHandler;
	/**
	 * 用户未登录处理
	 */
	@Resource
	private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
	/**
	 * 用户没有权限时处理
	 */
	@Resource
	private UserAuthAccessDeniedHandler         userAuthAccessDeniedHandler;
	/**
	 * 登出成功的处理
	 */
	@Resource
	private LogoutSuccessHandler                logoutSuccessHandler;
	/**
	 * token认证过滤器
	 */
	@Resource
	private JwtAuthenticationTokenFilter        authenticationTokenFilter;

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		// 使用BCrypt加密密码
		return new BCryptPasswordEncoder();
	}

	/**
	 * 跨域配置
	 */
	@Bean
	public CorsFilter corsFilter()
	{
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// 设置访问源地址
		config.addAllowedOrigin("*");
		// 设置访问源请求头
		config.addAllowedHeader("*");
		// 设置访问源请求方法
		config.addAllowedMethod("*");
		// 对接口配置跨域设置
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	/**
	 * 为了能抛出UsernameNotFoundException，自定义DaoAuthenticationProvider，将hideUserNotFoundExceptions属性设置为false
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		provider.setHideUserNotFoundExceptions(false);
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// 为了能抛出UsernameNotFoundException，自定义DaoAuthenticationProvider，将hideUserNotFoundExceptions属性设置为false
		// 所以这个配置不需要了，在daoAuthenticationProvider已经配置
		// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		// daoAuthenticationProvider()相当于自定义provider
		auth.authenticationProvider(daoAuthenticationProvider());
		// 暂时不用自定义的，以后扩展复杂验证时再使用
		// auth.authenticationProvider(userAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable();//禁用了 csrf 功能
		http.authorizeRequests()
				// 放行接口
				.antMatchers(Constants.AUTH_WHITELIST).permitAll()
				// 除上面外的所有请求全部需要鉴权认证，使用注解方式
				.anyRequest().authenticated()
				// 异常处理(权限拒绝、登录失效等) 配置没有权限自定义处理类
				.and().exceptionHandling()
				// 登录用户没有权限访问资源
				.accessDeniedHandler(userAuthAccessDeniedHandler)
				// 用户未登录时处理
				.authenticationEntryPoint(userAuthenticationEntryPointHandler)
				// 登入
				.and().formLogin().permitAll()//允许所有用户
				.loginProcessingUrl("/login")//登录请求路径
				.successHandler(loginSuccessHandler)//登录成功处理逻辑
				.failureHandler(loginFailureHandler)//登录失败处理逻辑
				// 登出
				.and().logout().permitAll()//允许所有用户
				.logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
				// .deleteCookies(RestHttpSessionIdResolver.AUTH_TOKEN)
				// 会话管理
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //基于token，所以不需要session
		// 添加JWT filter
		http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		// 退出登录配置跨域
		http.addFilterBefore(corsFilter(), LogoutFilter.class);
	}
}
