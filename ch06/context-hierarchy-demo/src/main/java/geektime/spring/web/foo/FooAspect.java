package geektime.spring.web.foo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author xschen
 */

@Aspect
@Slf4j
public class FooAspect {

    // 拦截所有以testBean打头的bean
    @AfterReturning("bean(testBean*)")
    public void printAfter() {
        log.info("after hello()");
    }
}
