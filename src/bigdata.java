import org.apache.commons.io.IOUtils;
import org.junit.Test;
import utils.JdbcUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;

/**
 * 大类型数据
 * author:zhouzhongzhong
 * date:2021/11/16,21:52
 */
public class bigdata {
    /**
     * 把mp3保存到数据库中
     */
    @Test
    public void fun1() throws SQLException, IOException {
        /**
         * 得到connection
         * 给出sql模板，创建preparedStatement
         * 设置sql模板中的参数
         * 调用preparedStatement的executeUpdate()执行
         */
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into tab_bin values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "戴荃 - 悟空.mp3");
        /**
         * 需要得到Blob
         * 1、我们有的是mp3文件，目标是Blob
         * 2、先把文件变成byte[]
         * 3、再使用byte[]创建Blob
         */
        //把文件变成byte[]数组
        byte[] bytes = IOUtils.toByteArray(new FileInputStream("D:\\CloudMusic\\戴荃 - 悟空.mp3"));
        //使用byte[]创建blob
        Blob blob = new SerialBlob(bytes);
        //设置第三个参数
        preparedStatement.setBlob(3, blob);

        preparedStatement.executeUpdate();
    }

    /**
     * 从数据库中读取mp3
     */
    @Test
    public void fun2() throws SQLException, IOException {
        /**
         * 1、创建Connection
         */
        Connection connection = JdbcUtils.getConnection();
        /**
         * 2、给出select语句模板，创建preparedStatement对象
         */
        String sql = "select * from tab_bin";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        /**
         * 3、preparedStatement执行查询，得到ResultSet
         */
        ResultSet resultSet = preparedStatement.executeQuery();
        /**
         * 4、获取resultSet中名为data的列数据
         */
        if (resultSet.next()) {
            Blob blob = resultSet.getBlob("data");
            /**
             * 把Blob变成硬盘上的文件
             * 1、通过Blob得到输入流对象
             * 2、自己创建输出流对象
             * 3、把输入流的数据写入到输出流中
             */
            InputStream inputStream = blob.getBinaryStream();
            OutputStream outputStream = new FileOutputStream("D:\\CloudMusic\\悟空.mp3");
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
