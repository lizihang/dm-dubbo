package com.dm.aop;

import org.springframework.stereotype.Component;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月25日 10:49</p>
 * <p>类全名：com.dm.aop.TestUserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class TestUserService {
    @TestDmLog
    public String testAop() {
        System.out.println("true method");
        throw new RuntimeException("oh,exception");
        //return "hello world";
    }

    @TestDmLog
    public void testAopNoReturn() {
        System.out.println("true method no return");
        throw new RuntimeException("oh,exception");
    }
}
