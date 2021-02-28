package geektime.spring.data.declarativetransactiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring的声明式事务本质上是通过AOP来增强了类的功能
 * Spring的AOP本质上就是为类做了一个代理，看似在调用自己的写的类，实际用的是增强后的代理类。
 *  问题的解决：访问增强后的代理类方法，而非直接访问自身的方法
 */
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    // rollbackException 抛出这个异常时执行回滚
    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

//    @Override
//    public void invokeInsertThenRollback() throws RollbackException {
//        insertThenRollback();
//    }


    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        // 问题的解决：访问增强后的代理类方法，而非直接访问自身的方法
        fooService.insertThenRollback();
    }
}
