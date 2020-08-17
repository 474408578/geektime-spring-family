package geektime.spring.springbucks.waiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author xschen
 */


@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
public class WaiterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceApplication.class);
    }
}
