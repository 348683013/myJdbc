package ThreadLocal;

import org.junit.Test;

/**
 * author:zhouzhongzhong
 * date:2021/11/20,16:29
 */
public class Demo {
    @Test
    public void fun1(){
        //就只有下面这三个方法
        ThreadLocal<String> t1 = new ThreadLocal<>();
        t1.set("hello");
        System.out.println(t1.get());
        t1.remove();
    }
}
