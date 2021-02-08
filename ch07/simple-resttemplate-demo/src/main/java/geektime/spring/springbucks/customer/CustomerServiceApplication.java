package geektime.spring.springbucks.customer;

import geektime.spring.springbucks.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication implements ApplicationRunner {
	@Autowired
	private RestTemplate restTemplate;// 类似于httpClient, 客户端发起请求

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(CustomerServiceApplication.class) // 以当前类作为配置的source
				.bannerMode(Banner.Mode.OFF) // 不需要banner
				.web(WebApplicationType.NONE) // 使用NONE，不用启动以一个tomcat
				.run(args); // 运行
	}

	/**
	 * spring boot 默认没有自动配置restTemplate，需要我们自己new RestTemplate
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
		return builder.build();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1);
		// 发起http请求，获取coffee对象的ResponseEntity
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee: {}", c.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
//				.name("Americano")
				.price(BigDecimal.valueOf(25.00))
				.build();
		// 发起post请求，获取coffee对象
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		String s = restTemplate.getForObject(coffeeUri, String.class);
		log.info("String: {}", s);
	}
}
