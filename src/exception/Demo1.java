package exception;

import org.junit.Test;

/**
 * 异常测试
 *
 * author:zhouzhongzhong
 * date:2021/11/18,22:09
 */
public class Demo1 {
    @Test
    public void fun1() throws Exception {
        throw new RuntimeException("异常");
    }
}
