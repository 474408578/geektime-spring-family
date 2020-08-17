package geektime.spring.springbucks.waiter.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xschen
 */

@Entity // 必选注解，表示类是一个实体类，接受jpa控制管理，对应数据库中的一个表
@Table(name = "T_COFFEE")
@Builder
@Data
@EqualsAndHashCode(callSuper = true) // 生成equals(Object other)和hashCode()方法
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity implements Serializable {  // Serializable需要两次？
    private String name;

    // 通过type将money对象映射为数据库中的bigint类型
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
    parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

}
