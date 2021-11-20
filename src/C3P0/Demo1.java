package C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0
 * author:zhouzhongzhong
 * date:2021/11/18,20:13
 */
public class Demo1 {
    /**
     * 手动配置
     * @throws PropertyVetoException
     * @throws SQLException
     */
    @Test
    public void fun1() throws PropertyVetoException, SQLException {
        //创建连接池对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //四大参数配置
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUser("root");
        dataSource.setPassword("123456");

        //池配置
        dataSource.setAcquireIncrement(5);
        dataSource.setInitialPoolSize(20);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(50);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    /**
     * 配置文件的默认配置 c3p0-config.xml
     */
    @Test
    public void fun2() throws SQLException {
        /**
         * 默认的会读取不到，所以需要自己设置命名的配置文件
         */
        ComboPooledDataSource dataSource = new ComboPooledDataSource("test");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
