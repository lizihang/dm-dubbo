package com.dm.system.service.impl;

import com.dm.system.po.DmUser;
import com.dm.system.service.UserUIService;
import com.dm.system.util.JwtTokenUtil;
import com.dm.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 * <p>创建日期：2020年12月09日 10:20</p>
 * <p>类全名：com.dm.system.service.impl.UserDetailsServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service(UserDetailsServiceImpl.BeanName)
public class UserDetailsServiceImpl implements UserDetailsService
{
	public static final  String BeanName = "UserDetailsService";
	private static final Logger logger   = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Resource
	UserUIService userUIService;
	@Resource
	JwtTokenUtil  jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		DmUser user = userUIService.queryUserByUsernameUI(username);
		if (user == null)
		{
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		}
		return createLoginUser(user);
	}

	public UserDetails createLoginUser(DmUser user)
	{
		//TODO 转换对象
		logger.info("[用户登录]-将DmUser转换成LoginUser");
		LoginUser loginUser = new LoginUser(user);
		// 1.生成token
		loginUser.setToken(jwtTokenUtil.generateToken(loginUser));
		// 2.处理权限
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("/user/queryList");
		grantedAuthorities.add(grantedAuthority);
		loginUser.setAuthorities(grantedAuthorities);
		// 3.处理其他信息
		return loginUser;
	}
}
