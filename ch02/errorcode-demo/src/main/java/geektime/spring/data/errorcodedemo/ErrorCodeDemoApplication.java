package geektime.spring.data.errorcodedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

/**
 * @see SQLErrorCodeSQLExceptionTranslator  解析错误码
 * Error定义 :
 *      org.springframework.jdbc.support.sql-error-codes.xml
 *      classpath:sql-error-codes
 *
 * @author xschen
 */

@SpringBootApplication
public class ErrorCodeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorCodeDemoApplication.class, args);
    }
}
