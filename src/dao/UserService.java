package dao;

import org.junit.Test;

/**
 * author:zhouzhongzhong
 * date:2021/11/16,19:37
 */
public class UserService {
    @Test
    public void toSay(){
        UserDao user = DaoFactory.getUserDao();
        user.say();
    }
}
