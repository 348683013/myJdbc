package dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * 1、通过配置文件得到dao实现类的名称
 * 2、通过类名称，完成创建类对象（反射完成的）
 *
 * author:zhouzhongzhong
 * date:2021/11/16,19:38
 */
public class DaoFactory {
    private static Properties properties = null;

    static { //加载配置文件中的内容到properties中
        try {
            InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("dao/dao.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDao getUserDao() {
        /**
         * 给出一个配置文件，文件中给出UserDao 接口的实现类名称
         * 通过这个方法获取实现类的类名，通过反射完成创建对象
         */
        String daoClassName = properties.getProperty("dao.UserDao");

        //通过反射来创建实现类的对象
        try {
            Class clazz = Class.forName(daoClassName);
            return (UserDao) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
