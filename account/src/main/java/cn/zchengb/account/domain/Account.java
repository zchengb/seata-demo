package cn.zchengb.account.domain;

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
@Table(schema = "account")
public class Account {
    @Id
    private Long id;
    private long balance;
    @Embedded
    private RecordTime recordTime;

    public void deduct(long deductValue) {
        if (this.balance < deductValue) {
            throw new IllegalArgumentException("remaining balance doesn't enough to deduct.");
        }

        this.balance -= deductValue;
    }
}
