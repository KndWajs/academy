package info.wajs.academy.rest.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MyApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyApp.class, args);
    }
}