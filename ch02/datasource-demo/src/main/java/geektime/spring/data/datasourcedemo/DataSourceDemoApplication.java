package geektime.spring.data.datasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
@Slf4j
public class DataSourceDemoApplication implements CommandLineRunner {
//	private static Logger log = LoggerFactory.getLogger(DataSourceDemoApplication.class);
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DataSourceDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}

	private void showConnection() throws SQLException {
		// 此处需要在idea中引入lombok插件，防止变红。
		log.info(dataSource.toString());
		// 取一个连接出来
		Connection conn = dataSource.getConnection();
		log.info(conn.toString());
		conn.close();
	}

	// 需要进行一些jdbc操作
	private void showData() {
		jdbcTemplate.queryForList("SELECT * FROM FOO")
				.forEach(row -> log.info(row.toString()));
	}
}

