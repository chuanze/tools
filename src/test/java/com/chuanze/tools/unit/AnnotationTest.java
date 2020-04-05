package com.chuanze.tools.unit;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

/**
 * @Description: 单元测试注解测试
 * @Author A05155
 * @Date 2020-4-2 9:41
 * @Version V1.0
 **/
public class AnnotationTest {
    //execute before class
    @BeforeAll
    public static void beforeClass() {
        System.out.println("in before all");
    }

    //execute after class
    @AfterAll
    public static void afterClass() {
        System.out.println("in after all");
    }

    //execute before test
    @BeforeEach
    public void before() {
        System.out.println("in before method");
    }

    //execute after test
    @AfterEach
    public void after() {
        System.out.println("in after method");
    }

    //test case
    @Test
    public void test() {
        System.out.println("in test");
    }

    @Test
    public void test2() {
        System.out.println("in test2");
    }

    //test case ignore and will not execute
    @Ignore
    public void ignoreTest() {
        System.out.println("in ignore test");
    }
}
