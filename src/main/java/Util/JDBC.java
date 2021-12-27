package Util;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;
@Configuration
@ComponentScan({"Util"})
@PropertySource("classpath:druid.properties")
@Import(Base.class)
public class JDBC {
    @Value("${url}")
    private String url;
    @Value("${username1}")
    private String username1;
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${password}")
    private String password;
    @Bean()
    public  DataSource getDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username1);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }
}
