package cn.zchengb.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
@Embeddable
@AllArgsConstructor
public class RecordTime {
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public RecordTime() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public static RecordTime now() {
        return new RecordTime();
    }

    @PrePersist
    public void initTime() {
        if (Objects.isNull(createTime)) {
            createTime = LocalDateTime.now();
        }
        if (Objects.isNull(updateTime)) {
            updateTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdateTime() {
        if (Objects.isNull(createTime)) {
            createTime = LocalDateTime.now();
        }
        updateTime = LocalDateTime.now();
    }
}
