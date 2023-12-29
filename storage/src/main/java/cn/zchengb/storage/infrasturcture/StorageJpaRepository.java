package cn.zchengb.storage.infrasturcture;

import cn.zchengb.storage.domain.Storage;
import cn.zchengb.storage.domain.StorageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageJpaRepository extends StorageRepository, JpaRepository<Storage, Long> {
}
