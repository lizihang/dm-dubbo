package com.dm.test;

import java.util.Scanner;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 题目描述：
 * 小华是个很有对数字很敏感的小朋友，他觉得数字的不同排列方式有特殊美感。某天，小华突发奇想，如果数字多行排列，第一行1个数，第二行2个，第三行3个，即第n行有n个数字，并且奇数行正序排列，偶数行逆序排列，数字依次累加。这样排列的数字一定很有意思。聪明的你能编写代码帮助小华完成这个想法吗？
 * 规则总结如下：
 * a、每个数字占据4个位置，不足四位用‘*’补位，如1打印为1***。
 * b、数字之间相邻4空格。
 * c、数字的打印顺序按照正序逆序交替打印,奇数行正序，偶数行逆序。
 * d、最后一行数字顶格，第n-1行相对第n行缩进四个空格
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月26日 21:06</p>
 * <p>类全名：com.dm.test.Test2</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test2
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		// 总行数
		int totalLine = in.nextInt();
		// 总数
		int total = 0;
		for (int i = 0; i < totalLine; i++)
		{
			// 第一行前面空格数(不乘以4的)
			int space = totalLine - 1 - i;
			printLine(space, i + 1, total);
			total += i + 1;
		}
	}

	private static void printLine(int space, int line, int total)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < space; i++)
		{
			sb.append("    ");
		}
		// 1.计算当前行要打印几个数
		int num = line;
		// 2.当前行起始的数是几
		int start = total + 1;
		if (line % 2 == 0)
		{
			//
			int end = start + num - 1;
			// 偶数行倒叙
			String s = "";
			for (int i = 0; i < num; i++)
			{
				sb.append(dealOne(end - i)).append("    ");
			}
		} else
		{
			// 奇数行正顺序
			String s = "";
			for (int i = 0; i < num; i++)
			{
				sb.append(dealOne(start + i)).append("    ");
			}
		}
		System.out.println(sb.toString());
	}

	/**
	 * 补全4位的*
	 * @param num
	 * @return
	 */
	private static String dealOne(int num)
	{
		StringBuilder s = new StringBuilder(num + "");
		int sLen = s.length();
		if (sLen < 4)
		{
			for (int i = 0; i < 4 - sLen; i++)
			{
				s.append("*");
			}
		}
		return s.toString();
	}
}
