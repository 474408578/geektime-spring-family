package geektime.spring.springbucks.waiter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器的实现
 */

@Slf4j
public class PerformanceInterceptor implements HandlerInterceptor {

    private ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        StopWatch sw = new StopWatch();
        stopWatch.set(sw);
        sw.start();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        stopWatch.get().stop();
        stopWatch.get().start(); //
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        StopWatch sw = stopWatch.get();
        sw.stop();
        // 执行的是Controller方法
        String method = handler.getClass().getSimpleName();
        if (handler instanceof HandlerMethod) {
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            method = beanType + "." + methodName;
        }
        log.info("{};{};{};{};{}ms;{}ms;{}ms",
                request.getRequestURI(),
                method,
                response.getStatus(),
                ex == null ? "-" : ex.getClass().getSimpleName(),
                sw.getTotalTimeMillis(),
                sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis(), // preHandle到postHandle的时间
                sw.getLastTaskTimeMillis()); // postHandle到afterCompletion的耗时
        stopWatch.remove();
    }
    /**
     * 2020-10-11 16:28:45.974  INFO 4420 --- [nio-8080-exec-2] g.s.s.w.c.PerformanceInterceptor :
     * /order/;
     * geektime.spring.springbucks.waiter.controller.CoffeeOrderController.create;
     * 201;
     * -;
     * 718ms;
     * 718ms;
     * 0ms
     */
}
