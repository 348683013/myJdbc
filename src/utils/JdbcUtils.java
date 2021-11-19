package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * V 1.0 版本
 * author:zhouzhongzhong
 * date:2021/11/16,16:24
 */
public class JdbcUtils {

    private static Properties properties = null;

    //只在JdbcUtils类被加载时执行一次，这个类每次被加载都先执行静态代码块，并且不管加载几次这个类，这个静态代码块只执行一次，并且是第一次执行。
    static {
        try {
            //加载配置文件 dbconfig.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("utils/dbconfig.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载驱动类
        try {
            Class.forName(properties.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 得到和数据库的连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        //得到connection并返回
        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }
}
