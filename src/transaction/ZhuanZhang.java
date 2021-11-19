package transaction;

import org.junit.Test;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 转账——事务
 * author:zhouzhongzhong
 * date:2021/11/17,21:08
 */
public class ZhuanZhang {
    /**
     * 转账方法
     *
     * @param from
     * @param to
     * @param money
     */
    public void zhuanZhang(String from, String to, double money) throws SQLException {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();

            //开启事务
            connection.setAutoCommit(false);

            AccountDao dao = new AccountDao();
            dao.updateBalance(connection, from, -money); //减去相应的金额
            dao.updateBalance(connection, to, money); //加上相应的金额

            //提交事务
            connection.commit();
            connection.close();
        } catch (Exception e) {
            //回滚事务
            connection.rollback();
            connection.close();
        }
    }

    @Test
    public void fun1() {
        try {
            ZhuanZhang zz = new ZhuanZhang();
            zz.zhuanZhang("张三", "李四", 111000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
