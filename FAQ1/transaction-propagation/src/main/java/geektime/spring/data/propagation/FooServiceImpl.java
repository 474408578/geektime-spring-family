package geektime.spring.data.propagation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Propagation.REQUIRES_NEW: 始终启动一个新事务，两个事务之间没有关联
 *
 * Propagation.NESTED: 在原事务内启动一个内嵌事务：
 *      两个事务有关联
 *      外部事务回滚，内部事务也会回滚
 *      内部事务回滚，不会影响外部事务回滚
 *
 * @author xschen
 */

@Slf4j
@Component
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    /**
     * rollbackFor 指定能够触发事务回滚的异常类型
     * @throws RollbackException
     */
//    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.NESTED)
    @Override
    public void insertAndRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void invokeInsertAndRollback() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        try {
            fooService.insertAndRollback();
        } catch (RollbackException exception) {
            log.error("RollbackException: {}", exception);
        }
//        throw new RuntimeException();
    }
}
