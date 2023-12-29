package cn.zchengb.storage.application;

import cn.zchengb.storage.domain.Storage;
import cn.zchengb.storage.domain.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageApplicationService {
    private final StorageRepository storageRepository;

    public void decreaseStorage(long storageId, int quantity) {
        var storage = findStorage(storageId);
        storage.decreaseStorage(quantity);
        storageRepository.save(storage);
    }

    public long getLatestPrice(long storageId) {
        return findStorage(storageId).getPrice();
    }

    private Storage findStorage(long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> new IllegalArgumentException("storage not found"));
    }
}
