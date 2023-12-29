package cn.zchengb.storage.domain;

import java.util.Optional;

public interface StorageRepository {
    Optional<Storage> findById(long storageId);

    Storage save(Storage storage);
}
