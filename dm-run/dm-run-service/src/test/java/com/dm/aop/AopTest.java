package com.dm.aop;

import com.dm.DmServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月24日 11:04</p>
 * <p>类全名：com.dm.aop.AopTest</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DmServiceApplication.class)
public class AopTest {
    @Autowired
    TestUserService testUserService;

    // 正常情况通知执行顺序
    // doAround before
    // doBefore
    // true method no return
    // doAfterReturning
    // doAfter
    // doAround after

    // 异常情况通知执行顺序
    // doAround before
    // doBefore
    // true method
    // oh,exception
    // doAfter

    /**
     * 测试插入
     */
    @Test
    public void TestAop() {
        String s = testUserService.testAop();
        System.out.println(s);
    }

    @Test
    public void TestAopNoReturn(){
        testUserService.testAopNoReturn();
    }
}
