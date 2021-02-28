package geektime.spring.data.propagation;

/**
 * @author xschen
 */
public interface FooService {
    void insertAndRollback() throws RollbackException;
    void invokeInsertAndRollback() throws RollbackException;
}
