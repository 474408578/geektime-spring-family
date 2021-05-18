package geektime.spring.springbucks.waiter;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import geektime.spring.springbucks.waiter.controller.PerformanceInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 * @see WebMvcConfigurer#addInterceptors(InterceptorRegistry)
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addInterceptor 注册拦截器
		// addPathPatterns 拦截规则
		registry.addInterceptor(new PerformanceInterceptor())
				.addPathPatterns("/coffee/**")
				.addPathPatterns("/order/**");
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);  // 缩进
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 时间使用上海的时区
		};
	}
}
