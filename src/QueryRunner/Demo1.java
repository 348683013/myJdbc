package QueryRunner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;
import utils2.JdbcUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * author:zhouzhongzhong
 * date:2021/11/22,12:04
 */
public class Demo1 {
    /**
     * 增删改方法
     *
     * @throws SQLException
     */
    @Test
    public void fun1() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?)";
        Object[] params = {2, "李四", 23, "lisi", "lisi123456"};
        queryRunner.update(sql, params);
    }

    /**
     * 查询一条记录方法
     */
    @Test
    public void fun2() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from user where id=?";
        Object[] params = {2};
        Stu stu = queryRunner.query(sql, new BeanHandler<Stu>(Stu.class), params);
        System.out.println(stu);
    }

    /**
     * 查询所有记录的方法
     *
     * @throws SQLException
     */
    @Test
    public void fun3() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from user";
        List<Stu> list = qr.query(sql, new BeanListHandler<Stu>(Stu.class));
        for (Object stu : list) {
            System.out.println(stu);
        }
    }

    /**
     * 使用map存放单行记录，键值对，键值是列名，值是列名对应的值
     */
    @Test
    public void fun4() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from user";
        Map map = qr.query(sql, new MapHandler());
        for (Object key : map.keySet()) {
            System.out.println(key + "：" + map.get(key));
        }
    }

    /**
     * MapListHandler，多行记录查询，List<Map<String,Object>>
     */
    @Test
    public void fun5() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from user";
        List<Map<String, Object>> mapList = queryRunner.query(sql, new MapListHandler());
        for (Map<String, Object> map : mapList) {
            for (String key : map.keySet()) {
                System.out.print(map.get(key) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * ScalarHandler，单行单列使用，适合查询数量count(*)
     */
    @Test
    public void fun6() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select count(*) from user";
        Number num = (Number) queryRunner.query(sql, new ScalarHandler<>());

        int n = num.intValue();
        System.out.println(n);
    }
}
