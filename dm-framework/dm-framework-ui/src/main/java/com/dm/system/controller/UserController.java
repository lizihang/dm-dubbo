package com.dm.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.system.annotation.DmLog;
import com.dm.system.param.DmUserQueryParams;
import com.dm.system.po.DmUser;
import com.dm.system.service.UserUIService;
import com.dm.system.util.JwtTokenUtil;
import com.dm.system.util.ServletUtil;
import com.dm.system.vo.LoginUser;
import com.dm.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月21日 19:21</p>
 * <p>类全名：com.dm.com.dm.system.controller.UserController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("user")
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	JwtTokenUtil  jwtTokenUtil;
	@Resource
	UserUIService userUIService;

	/**
	 * 查询所有用户列表
	 * @param params 查询条件
	 * @return 分页数据
	 */
	@DmLog
	@GetMapping("queryUserList")
	@PreAuthorize("@ps.permission('user:query')")
	public Result queryUserList(DmUserQueryParams params)
	{
		Map<String,Object> data = new HashMap<>();
		Page<DmUser> page = userUIService.queryPageUI(params);
		data.put("list", page.getRecords());
		data.put("total", page.getTotal());
		return Result.success("查询用户列表成功", data);
	}

	/**
	 * 根据用户名查询用户信息
	 * @param params 查询条件
	 * @return 用户信息
	 */
	@DmLog
	@GetMapping("queryUser")
	@PreAuthorize("@ps.permission('user:query')")
	public Result queryUser(DmUserQueryParams params)
	{
		DmUser user = userUIService.queryUserUI(params);
		return Result.success("查询用户成功", user);
	}

	/**
	 * 查询当前登录用户信息
	 * @return
	 */
	@DmLog
	@GetMapping("queryUserInfo")
	@PreAuthorize("@ps.permission('user:query')")
	public Result queryUserInfo()
	{
		LoginUser loginUser = jwtTokenUtil.getLoginUser(ServletUtil.getRequest());
		String username = loginUser.getUsername();
		DmUser user = userUIService.queryUserByUsernameUI(username);
		return Result.success("查询用户信息成功", user);
	}

	/**
	 * 注册用户
	 * @param user 用户
	 * @return result
	 */
	@DmLog
	@PostMapping("register")
	public Result register(@RequestBody DmUser user)
	{
		userUIService.registerUI(user);
		return Result.success("用户注册成功!");
	}

	/**
	 * 用户更新
	 * @param user 用户
	 * @return result
	 */
	@DmLog
	@PostMapping("updateUser")
	@PreAuthorize("@ps.permission('user:update')")
	public Result updateUser(@RequestBody DmUser user)
	{
		userUIService.updateUserUI(user);
		return Result.success("用户修改成功");
	}

	/**
	 * 更新用户头像
	 * @param file
	 * @return
	 */
	@DmLog
	@PostMapping("updateAvatar")
	@PreAuthorize("@ps.permission('user:update')")
	public Result updateAvatar(@RequestParam("avatar") MultipartFile file)
	{
		if (!file.isEmpty())
		{
			String fileName = file.getOriginalFilename();
			int size = (int) file.getSize();
			System.out.println(fileName + "-->" + size);
			String path = "D:/dm/upload/avatar/";
			LoginUser loginUser = jwtTokenUtil.getLoginUser(ServletUtil.getRequest());
			DmUser user = loginUser.getUser();
			String username = user.getUsername();
			String avatar = username + "/" + fileName;
			File dest = new File(path + avatar);
			if (!dest.getParentFile().exists())
			{
				//判断文件父目录是否存在
				boolean success = dest.getParentFile().mkdirs();
				if (success)
				{
					logger.info("创建目录成功！" + dest.getAbsolutePath());
				} else
				{
					logger.error("创建目录失败！" + dest.getAbsolutePath());
				}
			}
			try
			{
				// 1.保存文件
				file.transferTo(dest);
				// 2.更新用户avatar字段
				user.setAvatar(avatar);
				userUIService.updateUserUI(user);
				// TODO
				// 3.更新缓存
				// 4.更新token
				// 5.返回结果
				return Result.success("头像上传成功", loginUser);
			} catch (IllegalStateException | IOException e)
			{
				e.printStackTrace();
			}
		}
		return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传文件不能为空！");
	}

	/**
	 * 逻辑删除
	 * @param userId 用户id
	 * @return
	 */
	@DmLog
	@DeleteMapping("deleteUserById/{userId}")
	@PreAuthorize("@ps.permission('user:delete')")
	public Result deleteUserByLogic(@PathVariable("userId") int userId)
	{
		userUIService.deleteUserByLogicUI(userId);
		return Result.success("删除成功，用户id：" + userId);
	}

	/**
	 * 彻底删除
	 * @param userId 用户id
	 * @return
	 */
	@DmLog
	@DeleteMapping("deleteCompletely/{userId}")
	@PreAuthorize("@ps.permission('user:delete')")
	public Result deleteCompletely(@PathVariable("userId") int userId)
	{
		userUIService.deleteUserUI(userId);
		return Result.success("删除成功，用户id：" + userId);
	}
}
