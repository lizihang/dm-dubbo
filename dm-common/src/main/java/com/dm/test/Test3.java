package com.dm.test;

import java.util.Scanner;
/**
 * <p>标题：华为线上编程题3</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：给一个正整数NUM1，计算出新正整数NUM2，NUM2为NUM1中移除N位数字后的结果，需要使得NUM2的值最小。
 * 输入描述：
 * 1.输入的第一行为一个字符串，字符串由0-9字符组成，记录正整数NUM1，NUM1长度小于32。
 * 2.输入的第二行为需要移除的数字的个数，小于NUM1长度。
 * 如：
 * 2615371
 * 4
 * 输出描述：
 * 输出一个数字字符串，记录最小值NUM2。
 * 如：131
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月26日 21:37</p>
 * <p>类全名：com.dm.test.Test3</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test3
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		// 原字符串
		String s = in.nextLine();
		int sLen = s.length();
		// 移除的数字的个数
		int b = in.nextInt();
		// 要保留的数
		int c = sLen - b;
		int[] indexs = new int[sLen - b];
		int point = 0;
		for (int i = 0; i < c; i++)
		{
			// 起始位置
			int start = i == 0 ? 0 : indexs[point > 0 ? point - 1 : 0] + 1;
			// 终止位置
			int end = sLen - c + i + 1;
			int min = 9;
			for (int j = start; j < end; j++)
			{
				String s1 = s.charAt(j) + "";
				int x = Integer.parseInt(s1);
				int flag = i == 0 ? 1 : 0;
				if (x < min && x >= flag)
				{
					min = x;
					indexs[point] = j;
				}
			}
			point++;
		}
		StringBuilder sb = new StringBuilder();
		for (int index : indexs)
		{
			sb.append(s.charAt(index));
		}
		System.out.println(sb.toString());
	}
}
