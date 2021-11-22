package utils2;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * V 2.0 版本
 * author:zhouzhongzhong
 * date:2021/11/16,16:24
 */
public class JdbcUtils {
    //c3p0连接池
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("test");

    //事务专用连接
    private static Connection connection = null;


    //使用连接池返回一个连接
    public static Connection getConnection() throws SQLException {
        //connection不等于null的时候表示事务已经开始了
        if (connection != null) {
            return connection;
        }
        return dataSource.getConnection();
    }

    //返回dataSource
    public static DataSource getDataSource(){
        return dataSource;
    }

    //开启事务
    //获取一个Connection,设置它的setAutoCommit(false)
    //还要保证dao中使用的连接是同一个连接,和下面commit和rollback的连接也是同一个连接
    public static void beginTransaction() throws SQLException {
        if (connection != null) {
            throw new SQLException("已经开启了事务，不能够重复开启！");
        }
        /**
         * 1、给connection赋值
         * 2、给connection设置为手动提交
         */
        connection = JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        System.out.println("开启事务...");
    }
    //提交事务
    //获取beginTransaction提供的Connection,然后调用commit方法
    public static void commitTransaction() throws SQLException {
        if (connection == null) {
            throw new SQLException("还没有开启事务，不能提交！");
        }
        /**
         * 使用connection.commit()
         */
        connection.commit();
        System.out.println("事务提交...");
        connection.close();
        connection = null;

    }
    //回滚事务
    //获取beginTransaction提供的Connection,然后调用rollback方法
    public static void rollbackTransaction() throws SQLException {
        if (connection == null) {
            throw new SQLException("还没有开启事务，不能回滚！");
        }
        /**
         * 使用connection.rollback()
         */
        connection.rollback();
        System.out.println("回滚事务...");
        connection.close();
        connection = null;

    }

    /**
     * 释放连接
     */
    public static void releaseConnection(Connection con) throws SQLException {
        //判断是否是是无连接，不是则关闭
        if (connection == null) {
            connection.close();
        }
        if (connection != con) {
            connection.close();
        }
    }
}
