package com.dm.test;

import java.util.Scanner;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 题目描述
 * 对一个数据a进行分类，分类方法为：此数据a（四个字节大小）的四个字节相加对一个给定的值b取模，如果得到的结果小于一个给定的值c，则数据a为有效类型，其类型为取模的值；如果得到的结果大于或者等于c，则数据a为无效类型。
 * 比如一个数据a=0x01010101，b=3，按照分类方法计算（0x01+0x01+0x01+0x01）%3=1，所以如果c=2，则此a为有效类型，其类型为1，如果c=1，则此a为无效类型；
 * 又比如一个数据a=0x01010103，b=3，按照分类方法计算（0x01+0x01+0x01+0x03）%3=0，所以如果c=2，则此a为有效类型，其类型为0，如果c=0，则此a为无效类型。
 * 输入12个数据，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据，请找到有效类型中包含数据最多的类型，并输出该类型含有多少个数据。
 * 输入：
 * 		3 4 256 257 258 259 260 261 262 263 264 265
 * 输出：
 * 		3
 * 说明：
 * 		10个数据4个字节相加后的结果分别为1 2 3 4 5 6 7 8 9 10，故对4取模的结果为1 2 3 0 1 2 3 0 1 2，c为3，所以0 1 2都是有效类型，类型为1和2的有3个数据，类型为0的只有2个数据，故输出3
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年12月26日 20:38</p>
 * <p>类全名：com.dm.test.Test</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int c = in.nextInt();
		int b = in.nextInt();
		int[] data = new int[10];
		for (int i = 0; i < 10; i++)
		{
			data[i] = in.nextInt();
		}
		// TODO 后来发现不一定10以内，应该长度为取模的值b
		// int[] results = new int[10];
		int[] results = new int[b];
		for (int x : data)
		{
			int result = js(x);
			int m = result % b;
			if (m < c)
			{
				results[m] += 1;
			}
		}
		int index = 0;
		int max = 0;
		for (int i = 0; i < results.length; i++)
		{
			if (results[i] > max)
			{
				index = i;
			}
		}
		System.out.println(results[index]);
	}

	private static int js(int x)
	{
		int result = 0;
		for (int i = 0; i < 4; i++)
		{
			result += x & 255;
			x = x >> 8;
		}
		return result;
	}
}
