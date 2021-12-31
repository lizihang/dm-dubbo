package com.dm.test;

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
	private static volatile Test4 test4;

	private Test4()
	{
	}

	public static Test4 getInstance()
	{
		if (test4 == null)
		{
			synchronized (Test4.class)
			{
				if (test4 == null)
				{
					test4 = new Test4();
				}
			}
		}
		return test4;
	}

	public static void main(String[] args)
	{
		String str = "([][][][]{]}{}({{}}))";
		boolean deal = deal2(str);
		System.out.println(deal);
	}

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

	private static boolean deal2(String str)
	{
		StringBuilder sb = new StringBuilder();
		if (str == null || str.length() == 0)
		{
			return true;
		}
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
}
