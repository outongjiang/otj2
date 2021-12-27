package Util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("UserServiceImpl")
@ComponentScan({"Dao","Controller"})
public class Base {
}
