package geektime.spring.web.foo;

import geektime.spring.web.context.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xschen
 */

@Configuration
@EnableAspectJAutoProxy
public class FooConfig {

    @Bean
    public TestBean testBeanX() {
//        return new TestBean("foo");
        return new TestBean("parentX");
    }

    @Bean
    public TestBean testBeanY() {
//        return new TestBean("foo");
        return new TestBean("parentY");
    }

    // 对foo进行aop增强
    @Bean
    public FooAspect fooAspect() {
        return new FooAspect();
    }
}
