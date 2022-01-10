package com.dm.test;

import java.util.LinkedList;
/**
 * <p>标题：华为一面编程题1</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 1、左括号必须用相同类型的右括号闭合。
 * 2、左括号必须以正确的顺序闭合。
 * 3、注意空字符串可被认为是有效字符串。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月31日 20:02</p>
 * <p>类全名：com.dm.test.Test4</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test4
{
	public static void main(String[] args)
	{
		String str = "([][][][]{]}{}({{}}))";
		boolean deal = deal2(str);
		System.out.println(deal);
	}

	/**
	 * 方法一：遍历字符串，用一个int数组存储三种括号的次数，
	 * 如果左括号，对应数组位置数值+1，如果右括号-1。
	 * 此种方法逻辑没有问题，但是实现复杂。需要记录括号出现的顺序。
	 * @param str
	 * @return
	 */
	private static boolean deal(String str)
	{
		String a = "([{";
		String b = ")]}";
		int[] result = new int[] { 0, 0, 0 };
		if (str == null || str.length() == 0)
		{
			return true;
		}
		int len = str.length();
		for (int i = 0; i < len; i++)
		{
			String _s = str.substring(i, i + 1);
			if (i == 0)
			{
				if (a.contains(_s))
				{
					result[a.indexOf(_s)] = 1;
				} else
				{
					System.out.println("1");
					return false;
				}
			} else
			{
				if (a.contains(_s))
				{
					result[a.indexOf(_s)] += 1;
				} else if (b.contains(_s))
				{
					String last = str.substring(i - 1, i);
					// 如果和前面括号是一对，直接-1
					if (b.indexOf(_s) == a.indexOf(last))
					{
						result[b.indexOf(_s)] -= 1;
					} else
					{
						// 如果不是一对，要看除了这个括号的其他括号的数组和都为0
						for (int j = 0; j < result.length; j++)
						{
							if (b.indexOf(_s) == j)
							{
								continue;
							}
							if (result[j] != 0)
							{
								System.out.println("2" + _s);
								return false;
							} else
							{
								result[b.indexOf(_s)] -= 1;
							}
						}
					}
				} else
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 方法二：StringBuilder实现，类似栈的思想，先进后出。
	 * 如果当前的括号是左括号则入栈，
	 * 如果是右括号，则判断前一个括号是不是其对应的左括号：
	 * 如果是则弹出左括号，不是则返回false。
	 * 最终StringBuilder为空说明括号成对消除了，返回true。
	 * @param str
	 * @return
	 */
	private static boolean deal2(String str)
	{
		if (str == null || str.length() == 0)
		{
			return true;
		}
		StringBuilder sb = new StringBuilder();
		int len = str.length();
		String a = "([{";
		String b = ")]}";
		for (int i = 0; i < len; i++)
		{
			String _s = str.substring(i, i + 1);
			if (i == 0)
			{
				if (a.contains(_s))
				{
					sb.append(_s);
				} else
				{
					return false;
				}
			} else
			{
				// 如果左括号，直接拼接
				if (a.contains(_s))
				{
					sb.append(_s);
				} else
				{
					//如果是右括号
					String last = sb.substring(sb.length() - 1);
					// 如果前一个是对应的左括号，那么消掉
					if (b.indexOf(_s) == a.indexOf(last))
					{
						sb.delete(sb.length() - 1, sb.length());
					} else
					{
						//	如果不是，
						return false;
					}
				}
			}
		}
		if (sb.length() == 0)
		{
			return true;
		} else
		{
			System.out.println(sb.toString());
			return false;
		}
	}

	/**
	 * 方法三：LinkedList实现，类似栈的思想，先进后出。
	 * 如果当前的括号是左括号则入栈，
	 * 如果是右括号，则判断前一个括号是不是其对应的左括号：
	 * 如果是则弹出左括号，不是则返回false。
	 * 最终LinkedList为空说明括号成对消除了，返回true。
	 * @param str
	 * @return
	 */
	private static boolean deal3(String str)
	{
		if (str == null || str.length() == 0)
		{
			return true;
		}
		LinkedList<String> list = new LinkedList<>();
		int len = str.length();
		String a = "([{";
		String b = ")]}";
		for (int i = 0; i < len; i++)
		{
			String _s = str.substring(i, i + 1);
			if (i == 0)
			{
				if (a.contains(_s))
				{
					list.add(_s);
				} else
				{
					return false;
				}
			} else
			{
				// 如果左括号，直接拼接
				if (a.contains(_s))
				{
					list.add(_s);
				} else
				{
					//如果是右括号
					String last = list.getLast();
					// 如果前一个是对应的左括号，那么消掉
					if (b.indexOf(_s) == a.indexOf(last))
					{
						list.removeLast();
					} else
					{
						//	如果不是，
						return false;
					}
				}
			}
		}
		if (list.size() == 0)
		{
			return true;
		} else
		{
			list.forEach(System.out::println);
			return false;
		}
	}
}
