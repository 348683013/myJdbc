package utils2;

import org.junit.Test;

import java.sql.SQLException;

/**
 * author:zhouzhongzhong
 * date:2021/11/19,15:30
 */
public class Demo {
    @Test
    public void serviceMethod(){
        try{
            JdbcUtils.beginTransaction();
            AccountDao.update("张三", -100);
            AccountDao.update("李四", 100);
            JdbcUtils.commitTransaction();
        } catch (Exception e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
