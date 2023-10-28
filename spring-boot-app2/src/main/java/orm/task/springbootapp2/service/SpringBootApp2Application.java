package orm.task.springbootapp2.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("orm.task.springbootapp2")
@EntityScan("orm.task.springbootapp2")
@ComponentScan("orm.task.springbootapp2")
public class SpringBootApp2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp2Application.class, args);
    }
}
