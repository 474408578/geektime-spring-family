package geektime.spring.web;

import geektime.spring.web.context.TestBean;
import geektime.spring.web.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xschen
 *
 * FooConfig.java : 父上下文（parent application context）
 * applicationContext.xml: 子上下文（child application context）
 *
 * 委托机制：在自己的context中找不到bean，就会委托父 context 查找该 bean
 *
 * 场景一：
 * 父上下文开启 @EnableAspectJAutoProxy 的支持
 * 子上下文未开启 <aop: aspectj-autoproxy />
 * 切面 fooAspect 在 FooConfig.java 定义（父上下文增强）
 *
 * 输出结果：
 * testBeanX(parentX) 和 testBeanY(parentY) 均被增强。
 * testBeanX(childX) 未被增强。
 *
 * 结论：
 * 在父上下文开启了增强，父的 bean 均被增强，而子的 bean 未被增强。
 *
 * -------------------------------------------------------------
 *
 * 场景二：
 * 父上下文开启 @EnableAspectJAutoProxy 的支持
 * 子上下文开启 <aop: aspectj-autoproxy />
 * 切面 fooAspect 在 applicationContext.xml 定义（子上下文）
 *
 * 输出结果：
 * testBeanX(parentX) 和 testBeanY(parentY) 未被增强。
 * testBeanX(childX) 被增强。
 *
 * 结论：
 * 在子上下文开启增强，父上下文中不开启增强，只有子上下文中被增强
 *
 * -----------------------------------------------------------
 * 场景三：
 * 父上下文开启 @EnableAspectJAutoProxy 的支持
 * 子上下文开启 <aop: aspectj-autoproxy />
 * 切面 fooAspect 在 FooConfig.xml 定义（父上下文）
 *
 *
 */

@SpringBootApplication
@Slf4j
public class ContextHierarchyDemoApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ContextHierarchyDemoApplication.class);
    }

    @Override
    public void run(ApplicationArguments args) {
        // 父 Spring 上下文
        ApplicationContext parentContext = new AnnotationConfigApplicationContext(FooConfig.class);
        // 子 Spring 上下文
        ClassPathXmlApplicationContext childContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"}, parentContext);

        // 从父 Spring 上下文中获取testBean，是否被增强（有没有打印出 After Hello）
        TestBean bean = parentContext.getBean("testBeanX", TestBean.class);
        bean.hello();
        log.info("=====================");

        // 从子 Spring 上下文获取 testBean， 看是否被增强
        bean = childContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        // 在子 Spring 上下文中查找testBeanY，未命中；委托父上下文查找，命中，返回testBeanY(foo)
        bean = childContext.getBean("testBeanY", TestBean.class);
        bean.hello();
    }
}
