import org.junit.Test;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 批处理
 * author:zhouzhongzhong
 * date:2021/11/17,19:40
 */
public class PiChuLi {
    /**
     * preparedStatement对象内部有集合
     * 1、使用循环疯狂向preparedStatement中添加sql参数，他自己有模板，使用一组参数与模板就可以匹配出一条sql语句
     * 2、调用它的执行批方法，完成向数据库发送
     */
    @Test
    public void fun1() throws SQLException {
        /**
         * 》 创建连接connection
         * 》 得到preparedStatement
         */
        Connection connection = JdbcUtils.getConnection();

        String sql="insert into user values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        /**
         * preparedStatement：
         * 》 添加sql需要的参数到批中
         * 》 执行批
         */
        //添加数据到sql
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setInt(1, i + 1);
            preparedStatement.setString(2, "stu_" + (i + 1));
            preparedStatement.setInt(3, i + 1);
            preparedStatement.setString(4, "username_" + (i + 1));
            preparedStatement.setString(5, "password_" + (i + 1));

            //添加到批中
            preparedStatement.addBatch();
        }

        //执行批
        preparedStatement.executeBatch();
    }
}
