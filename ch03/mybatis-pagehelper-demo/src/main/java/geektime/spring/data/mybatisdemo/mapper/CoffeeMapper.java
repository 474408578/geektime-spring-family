package geektime.spring.data.mybatisdemo.mapper;

import geektime.spring.data.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 查询的时候都没有声明PageHelper，而是传入RowBounds或者page，size这些参数就可以实现分页呢？
 * 是因为MyBatis有自己的Inteceptor
 */
@Mapper
public interface CoffeeMapper {
    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum,
                                  @Param("pageSize") int pageSize);
}
