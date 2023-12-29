package cn.zchengb.storage.domain;


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
@Table(schema = "storage")
public class Storage {
    @Id
    private Long id;
    private String name;
    private long price;
    private int quantity;
    @Embedded
    private RecordTime recordTime;

    public void decreaseStorage(int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("remaining quantity doesn't enough to deduct.");
        }

        this.quantity -= quantity;
    }
}
