package geektime.spring.data.datasourcedemo;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SpringBoot 通过 {@link DataSourceAutoConfiguration} 配置 DataSource
 * SpringBoot 通过 {@link DataSourceTransactionManagerAutoConfiguration} 配置 DataSourceTransactionManager
 * SpringBoot 通过 {@link JdbcTemplateAutoConfiguration} 配置JdbcTemplate
 * @see DataSourceAutoConfiguration
 * @see HikariDataSource#toString()
 */

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
		// 取一个连接出来 HikariProxyConnection
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

