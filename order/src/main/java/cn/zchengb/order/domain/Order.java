package cn.zchengb.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "order")
public class Order {
    @Id
    private Long id;
    private long accountId;
    private long storageId;
    private long price;
    private int quantity;
    @Embedded
    private RecordTime recordTime;

    public static Order create(long accountId, long storageId, long price, int quantity) {
        return Order.builder()
                .accountId(accountId)
                .storageId(storageId)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
