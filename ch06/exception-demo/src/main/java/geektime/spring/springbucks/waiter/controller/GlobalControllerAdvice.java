package geektime.spring.springbucks.waiter.controller;

import geektime.spring.springbucks.waiter.controller.exception.FormValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ControllerAdvice 这个注解会监听所有的controller，用于修饰类，表示该类是Controller的全局配置类。
 * 在此类中，可以对Controller进行如下三种全局配置：
 * 异常处理方案，绑定数据方案，绑定参数方案
 *
 * @ExceptionHander: 用于修饰方法，该方法会在Controller出现异常后被调用，用于处理捕获到的异常。
 *
 * @ModelAttibute: 用于修饰方法，该方法会在Controller方法执行前被调用，用于为Model对象绑定参数。
 *
 * @DataBinder：用于修饰方法，该方法会在Controller方法执行前被调用，用于绑定参数的转换器。
 *
 *
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    // 对ValidationException 做了自定义的处理方式
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 拦截到请求后响应对应的状态码
    public Map<String, String> validationExceptionHandler(ValidationException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return map;
    }
}
