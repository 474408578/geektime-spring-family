package geektime.spring.data.errorcodedemo;

import org.springframework.dao.DuplicateKeyException;

/**
 * @author xschen
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
