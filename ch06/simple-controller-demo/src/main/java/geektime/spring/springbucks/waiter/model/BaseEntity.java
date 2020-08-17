package geektime.spring.springbucks.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xschen
 */

@MappedSuperclass // BaseEntity作为基类，给其他类继承
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    @Id // 用于声明一个实体类的属性映射为数据库的主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 用于标注主键的生成策略，通过strategy属性指定
    private long id;

    @Column(updatable = false)  // 只读属性
    @CreationTimestamp // 可以让Hibernate在插入时, 针对注解的属性对应的日期类型创建默认值。
    private Date createTime;

    @UpdateTimestamp // 可以让Hibernate在更新时时针对注解的属性对应的日期类型创建默认值。
    private Date updateTime;

}
