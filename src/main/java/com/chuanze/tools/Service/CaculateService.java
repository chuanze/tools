package com.chuanze.tools.Service;

/**
 * @Description: 计算
 * @Author chuanze
 * @Date 2020-4-2 10:41
 * @Version V1.0
 **/
public class CaculateService {

    public Integer plus(Integer a, Integer b) {
        return a + b;
    }

    public Integer subtract(Object a, Integer b) {
        return (Integer) check(a) - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    public Object check(Object a) {
        if (a instanceof Integer) {
            System.out.println("It is Integer ~");
            return a;
        } else
            throw new RuntimeException("It is not a Integer~");
    }
}

