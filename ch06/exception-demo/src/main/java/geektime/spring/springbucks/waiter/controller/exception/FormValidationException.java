package geektime.spring.springbucks.waiter.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

// 当遇到这个异常的时候，指定http status为 400 bad request
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
public class FormValidationException extends RuntimeException {
    private BindingResult result;
}
