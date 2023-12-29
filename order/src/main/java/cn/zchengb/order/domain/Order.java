package cn.zchengb.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "order")
public class Order {
    @Id
    private Long id;
    private long accountId;
    private long stockId;
    private long price;
    private int quantity;
    @Embedded
    private RecordTime recordTime;

    public static Order create(long accountId, long stockId, long price, int quantity) {
        this.accountId = accountId;
        this.stockId = stockId;
        this.price = price;
        this.quantity = quantity;
    }
}
