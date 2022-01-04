package com.dm.test;

/**
 * <p>标题：华为一面编程题2</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * 提示：
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月31日 22:11</p>
 * <p>类全名：com.dm.test.Test5</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test5
{
	private static final String all = "abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args)
	{
		String s1 = "ab";
		String s2 = "eidboaoo";
		boolean result = deal(s1, s2);
		System.out.println("result:" + result);
	}

	private static boolean deal(String str1, String str2)
	{
		// 1.处理子串
		int[] arr1 = new int[26];
		dealOne(str1, arr1);
		// 2.处理
		for (int i = 0; i < str2.length() - str1.length(); i++)
		{
			int[] arr2 = new int[26];
			String s = str2.substring(i, i + str1.length());
			dealOne(s, arr2);
			boolean b = compareArr(arr1, arr2);
			if (b)
			{
				return true;
			}
		}
		return false;
	}

	private static void dealOne(String str, int[] arr)
	{
		for (int i = 0; i < str.length(); i++)
		{
			String _s = str.substring(i, i + 1);
			int index = all.indexOf(_s);
			arr[index] += 1;
		}
	}

	private static boolean compareArr(int[] arr1, int[] arr2)
	{
		for (int i = 0; i < arr1.length; i++)
		{
			if (arr1[i] != arr2[i])
			{
				return false;
			}
		}
		return true;
	}
}
