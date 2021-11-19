package utils;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * author:zhouzhongzhong
 * date:2021/11/16,16:24
 */
public class TestJdbcUtils {
    /**
     * 测试JdbcUtils得到connection
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void fun1() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
        Connection connection1 = JdbcUtils.getConnection();
        System.out.println(connection1);
    }
}
