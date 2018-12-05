import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan("com.course.server")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);   //args叫啥都行
    }
}
