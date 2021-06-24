package com.dm.system.controller;

import com.dm.system.annotation.DmLog;
import com.dm.system.param.DmDictQueryParams;
import com.dm.system.po.Dict;
import com.dm.system.po.DictInfo;
import com.dm.system.service.SysUIService;
import com.dm.system.util.ValidateCodeUtil;
import com.dm.system.vo.Menus;
import com.dm.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
 * <p>创建日期：2021年06月23日 15:53</p>
 * <p>类全名：com.dm.system.controller.SysController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin
@RestController
@RequestMapping("system")
public class SysController
{
	@Resource
	SysUIService sysUIService;

	/**
	 * 生成验证码
	 */
	@DmLog
	@GetMapping("/getCodeImg")
	public Result getCodeImg()
	{
		//直接调用静态方法，返回验证码对象
		ValidateCodeUtil.Validate v = ValidateCodeUtil.getRandomCode();
		Map<String,Object> data = new HashMap<>();
		if (v != null)
		{
			data.put("validCode", v.getValue().toLowerCase());
			data.put("validStr", v.getBase64Str());
		}
		return Result.success("获取验证码成功", data);
	}

	/**
	 * 验证token
	 * @return
	 */
	@DmLog
	@GetMapping("checkToken")
	public Result checkToken()
	{
		return Result.success("验证token通过");
	}

	/**
	 * 获取系统菜单
	 * @return
	 */
	@DmLog
	@GetMapping("getMenus")
	public Result getMenus()
	{
		List<Menus> menus = sysUIService.getMenusUI();
		return Result.success("获取系统菜单成功！", menus);
	}

	/**
	 * 获取字典列表
	 * @return
	 */
	@DmLog
	@GetMapping("queryDictList")
	public Result queryDictList(DmDictQueryParams params)
	{
		Map<String,Object> data = new HashMap<>();
		data.put("list", sysUIService.queryDictListUI(params));
		data.put("total", sysUIService.queryDictTotalUI(params));
		return Result.success("查询字典列表成功", data);
	}

	/**
	 * 获取字典信息
	 * @return
	 */
	@DmLog
	@GetMapping("queryDictInfo")
	public Result queryDictInfo(@RequestParam String dictId)
	{
		List<DictInfo> data = sysUIService.queryDictInfoUI(dictId);
		return Result.success("查询字典信息成功", data);
	}

	/**
	 * 新增字典
	 * @return
	 */
	@DmLog
	@PostMapping("addDict")
	public Result addDict(@RequestBody Dict dict)
	{
		sysUIService.addDictUI(dict);
		return Result.success("新增字典成功");
	}

	/**
	 * 修改字典
	 * @return
	 */
	@DmLog
	@PostMapping("updateDict")
	public Result updateDict(@RequestBody Dict dict)
	{
		sysUIService.updateDictUI(dict);
		return Result.success("修改字典成功");
	}

	@DmLog
	@DeleteMapping("deleteDict/{dictId}")
	public Result deleteDict(@PathVariable("dictId") String dictId)
	{
		sysUIService.deleteDictUI(dictId);
		return Result.success("删除字典编号<" + dictId + ">的字典成功！");
	}
}
