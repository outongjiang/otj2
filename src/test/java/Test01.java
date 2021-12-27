import Controller.UserService;
import Controller.UserServiceImpl;
import Dao.EmployeeDao;
import Dao.EmployeeDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Util.Base;
import Util.JDBC;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.weicoder.common.C;
import com.weicoder.common.W;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
class Person{
    void Person(){
        System.out.println("Person...");
    }
}
class student extends Person{
    void Person(){
        System.out.println("重写Person...");
    }
}
class MyApplicationContext{
    /*
     String path=Test01.class.getClassLoader().getResource(applicationContext).getPath();
        Document document=Jsoup.parse(path,"utf-8");
        Elements elements=document.getElementsByTag("class");
        String className="";
        String classId="";
        for (Element e:elements){
            className=e.attr("className");
            classId=e.attr("classId");
            if(classId.equals(id)){
                break;
            }
        }

        Class c =Class.forName(className);
        return c.newInstance();
    * */
    private String path;
    public MyApplicationContext(String p){
         path=MyApplicationContext.class.getClassLoader().getResource(p).getPath();

    }
    public Object getBean(String id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        Document document=Jsoup.parse(new File(path),"utf-8");
        Elements elements=document.getElementsByTag("class");
        String className="";
        String classId="";
        for (Element e:elements){
            className=e.attr("className");
            classId=e.attr("classId");
            if(classId.equals(id)){
                break;
            }
        }

        Class c =Class.forName(className);
        return c.newInstance();

    }

}
public class Test01 {
    @Test
    public void test1(){
        for(int i=0;i<10;i++) {
            System.out.println(UUID.randomUUID());
        }
    }
    @Test
    public void test2(){
        new student().Person();
    }
    @Test
    public void test3(){
        System.out.println("对比class");
        System.out.println(Person.class+"\t"+new Person().getClass());
    }
    @Test
    public void test4() throws IOException {
        String path=Test01.class.getClassLoader().getResource("1.xml").getPath();
        Document document =Jsoup.parse(new File(path),"utf-8");
        Elements elements=document.select("user[x=\"z\"]");
        for (Element e:elements) {
            System.out.println(e.text());
        }
    }
    @Test
    public void test5() throws IOException {
        String path=Test01.class.getClassLoader().getResource("Hello.class").getPath();
        System.out.println(path);
    }
    @Test
    public void test6() throws IOException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService= (UserService) applicationContext.getBean("UserService");
        userService.save();
//        EmployeeDao employeeDao= (EmployeeDao) applicationContext.getBean("UserDaoImpl");
//        employeeDao.save();
    }
//    @Test
//    public Object test7(String applicationContext,String id) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        //模拟spring内部操作
//        String path=Test01.class.getClassLoader().getResource(applicationContext).getPath();
//        Document document=Jsoup.parse(path,"utf-8");
//        Elements elements=document.getElementsByTag("class");
//        String className="";
//        String classId="";
//        for (Element e:elements){
//            className=e.attr("className");
//            classId=e.attr("classId");
//            if(classId.equals(id)){
//                break;
//            }
//        }
//
//        Class c =Class.forName(className);
//        return c.newInstance();
//    }
    @Test
    public void test8() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //模拟spring执行操作
//       UserDao userDao= (UserDao) test7("1.xml","UserDao");
//       userDao.save();
        MyApplicationContext myApplicationContext=new MyApplicationContext("1.xml");
        EmployeeDao employeeDao= (Dao.EmployeeDao) myApplicationContext.getBean("EmployeeDao");
        employeeDao.save();
    }
    @Test
    public void test9() throws NoSuchMethodException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService= applicationContext.getBean(UserService.class);
        userService.save();
    }
    @Test
    public void test10() throws NoSuchMethodException, IOException, SQLException {
            ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
            DruidDataSource druidDataSource=applicationContext.getBean(DruidDataSource.class);
            Connection connection=druidDataSource.getConnection();
            System.out.println(connection);
            connection.close();
    }
    @Test
    public void test11() throws NoSuchMethodException, IOException, SQLException {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(JDBC.class);
//        UserService userService=applicationContext.getBean(UserService.class);
//        userService.save();
        DataSource dataSource= (DataSource) applicationContext.getBean(DataSource.class);
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
        connection.close();
        ((AnnotationConfigApplicationContext)applicationContext).close();
    }
}
