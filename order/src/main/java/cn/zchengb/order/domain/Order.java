package cn.zchengb.order.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
                .recordTime(RecordTime.now())
                .build();
    }
}
