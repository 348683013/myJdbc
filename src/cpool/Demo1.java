package cpool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBCP连接池
 * author:zhouzhongzhong
 * date:2021/11/18,15:16
 */
public class Demo1 {
    @Test
    public void fun1() throws SQLException {
        /**
         * 1、创建连接池对象
         * 2、配置四大参数
         * 3、配置池参数
         * 4、得到连接对象
         */
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        dataSource.setMaxTotal(20);
        dataSource.setMinIdle(3);
        dataSource.setMaxWaitMillis(1000);

        //连接池是commons-dbcp.jar包里面的代码实现的，我们只需要设置参数就好了
        //上面代码就已经创建好连接池了
        //下面用连接池得到一个连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());

        /**
         * 连接池内部使用四大参数创建了连接对象，即mysql驱动提供的Connection
         * 连接池使用MySQL的连接对象进行了装饰，只对close()方法进行了增强
         * 装饰之后的Connection的close()方法，用来吧当前的连接归还给池
         */
        connection.close();
    }
}
