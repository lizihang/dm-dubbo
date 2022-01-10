package com.dm.test;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年01月07日 20:16</p>
 * <p>类全名：com.dm.test.Test6</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test6
{
	public static void main(String[] args)
	{
		int a = 12321;
		boolean deal = deal(a);
		System.out.println(deal);
	}

	private static boolean deal(int x)
	{
		String s = x + "";
		StringBuilder sb = new StringBuilder(s);
		StringBuilder reverse = sb.reverse();
		return s.equals(reverse.toString());
	}
}
