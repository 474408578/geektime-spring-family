package geektime.spring.springbucks.waiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xschen
 *
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceApplication.class);
    }
}
