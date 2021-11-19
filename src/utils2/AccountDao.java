package utils2;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

/**
 * author:zhouzhongzhong
 * date:2021/11/19,15:16
 */
public class AccountDao {
    public static void update(String name,double money) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update account set balance = balance + ? where name = ?";
        Object[] params = {money, name};

        //保证是同一个连接connection
        Connection connection = JdbcUtils.getConnection();

        queryRunner.update(connection, sql, params);
        System.out.println("");
    }
}
