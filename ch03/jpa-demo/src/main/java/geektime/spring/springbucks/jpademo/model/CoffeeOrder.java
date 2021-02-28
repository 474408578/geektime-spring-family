package geektime.spring.springbucks.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder implements Serializable {
    /**
     * create table t_order (
     *         id bigint not null,
     *         create_time timestamp,
     *         customer varchar(255),
     *         state integer not null,
     *         update_time timestamp,
     *         primary key (id)
     *     )
     */

    @Id
    @GeneratedValue
    private Long id;
    private String customer;

    /**
     * coffee 与 order 之间的关系是通过T_ORDER_COFFEE 这张映射表来做的
     */
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;

    @Column(nullable = false)
    private Integer state;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}

