package Dao;

public class UserDaoFactory {
    public  EmployeeDaoImpl getUserDaoImpl(){
        return new EmployeeDaoImpl();
    }
}
