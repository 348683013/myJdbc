import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 简化jdbc获取connection
 * author:zhouzhongzhong
 * date:2021/11/20,15:29
 */
public class JdbcUtils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("test");

    /**
     * 使用连接池获取connection
     */
    @Test
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 获取连接池对象
     */
    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }
}
