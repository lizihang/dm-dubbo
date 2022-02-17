package com.dm.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 题目一、我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年01月11日 20:02</p>
 * <p>类全名：com.dm.test.Test7</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Test7
{
	public static void main(String[] args)
	{
		int[][] points = { { 1, 3 }, { -2, 2 } };
		int[][] points2 = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
		int[][] points3 = { { 5, 77 }, { 5, -144 }, { -2, 14 }, { -2, 574 }, { -2, 4 }, { 0, 4 } };
		int[][] points4 = { { 5, 77 }, { 5, -144 }, { -2, 16 }, { -2, 4 }, { -2, 4 }, { 2, 4 } };
		int[][] points5 = { { 5, 77 }, { 5, -144 }, { -2, 138 }, { -452, 4 }, { -2, 4 }, { -5, 777 } };
		int[][] points6 = { { 5, 77 }, { 5, -144 }, { -2, 188 }, { 66, 88 }, { -453, 999 }, { -2, 4 } };
		int k = 3;
		int[][] results = kClosest(points6, k);
		for (int[] result : results)
		{
			System.out.println("[" + result[0] + "," + result[1] + "]");
		}
	}

	/**
	 * 1.第一种方法，当points.length非常大的时候，创建对象非常多，性能上不好
	 * 2.直接存k长度的数组中，记录第k个最小距离的point，如果大于第k的point则跳过，如果小于，则替换掉，并重新计算第k个最小距离的point
	 * @param points
	 * @param k
	 * @return
	 */
	public static int[][] kClosest(int[][] points, int k)
	{
		int[][] result = new int[k][];
		List<MyPoint> list = new ArrayList<>();
		for (int i = 0; i < points.length; i++)
		{
			MyPoint myPoint = new MyPoint();
			int[] p = points[i];
			int len = p[0] * p[0] + p[1] * p[1];
			myPoint.setIndex(i);
			myPoint.setLen(len);
			list.add(myPoint);
		}
		list.sort(Comparator.comparingInt(MyPoint::getLen));
		for (int i = 0; i < k; i++)
		{
			result[i] = points[list.get(i).getIndex()];
		}
		return result;
	}
}
class MyPoint
{
	private int index;
	private int len;

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public int getLen()
	{
		return len;
	}

	public void setLen(int len)
	{
		this.len = len;
	}
}

