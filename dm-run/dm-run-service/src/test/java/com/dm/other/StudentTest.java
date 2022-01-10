package com.dm.other;

import com.dm.DmServiceApplication;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年01月07日 15:39</p>
 * <p>类全名：com.dm.other.StudentTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class StudentTest
{
	@Resource
	StudentService studentService;
	private static final Random random = new Random();

	@Test
	public void insertStudent()
	{
		List<Student> data = new ArrayList<>();
		String[] subjects = { "yuwen", "shuxue", "yingyu", "lishi", "wuli" };
		for (int i = 0; i < 20; i++)
		{
			String name = RandomStringUtils.random(randomInt(), true, false);
			for (String subject : subjects)
			{
				Student student = new Student();
				student.setName(name);
				student.setGrade("1");
				student.setSubject(subject);
				student.setScore(random.nextInt(100));
				data.add(student);
			}
		}
		studentService.saveBatch(data);
	}

	private int randomInt()
	{
		return random.nextInt(10) + 10;
	}
}
