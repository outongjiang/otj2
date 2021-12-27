package Controller;import Dao.UserDao;import Dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
class User{
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void setAge(int age) {
        this.age = age;
    }
    String name;
    int age;
}
@Component("UserService")
public class UserServiceImpl implements UserService{
    private List<User> list;
    private Map<String,User>map;
    private Properties properties;
    @Value("张三")
    private String username;
    @Resource(name="UserDao")
    private UserDao userDao;
    public void setList(List<User> list) {
        this.list = list;
    }
    public void setMap(Map<String, User> map) {
        this.map = map;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化....");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁....");
    }
    @Override
    public void save() {
        System.out.println(this);
        userDao.save();
    }
    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "list=" + list +
                ", map=" + map +
                ", properties=" + properties +
                ", username='" + username + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}