package geektime.spring.data.druiddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

	/**
	 * CommandLineRunner接口的Component会在spring bean初始化之后，SpringApplication run之前执行，
	 * 可以控制在项目启动前初始化资源文件，比如初始化线程池，提前加载好加密证书等
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info(dataSource.toString());
	}
}

