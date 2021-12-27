package Dao;

import org.springframework.stereotype.Component;

@Component("UserDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save方法.....");
    }

    @Override
    public void init() {
        System.out.println("UserDaoImpl init 方法");
    }

    @Override
    public void destroy() {
        System.out.println("UserDaoImpl destroy 方法");
    }

}
