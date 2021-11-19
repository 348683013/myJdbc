package transaction;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 修改指定用户的余额
 * author:zhouzhongzhong
 * date:2021/11/17,21:10
 */
public class AccountDao {
    /**
     * 1、连接对象connection当成形式参数传进来，这样保证一个事务中的操作在同一个连接上
     * @param connection
     * @param name
     * @param balance
     */
    public void updateBalance(Connection connection ,String name, double balance) {
        try {
            /**
             * 2、给出sql模板，创建preparedStatement对象
             */
            String sql = "update account set balance=balance+? where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            /**
             * 3、补齐sql语句
             */
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, name);

            /**
             * 4、执行sql语句
             */
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
