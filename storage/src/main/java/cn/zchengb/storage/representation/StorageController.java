package cn.zchengb.storage.representation;

import cn.zchengb.storage.application.StorageApplicationService;
import cn.zchengb.storage.representation.request.StorageDeductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageApplicationService storageApplicationService;

    @GetMapping("/storages/{storage-id}/latestPrice")
    public long getStorageLatestPrice(@PathVariable("storage-id") long storageId) {
        return storageApplicationService.getLatestPrice(storageId);
    }

    @PostMapping("/storages/{storage-id}/deduction")
    public void deductStorage(@PathVariable("storage-id") long storageId, @RequestBody StorageDeductRequest request) {
        storageApplicationService.decreaseStorage(storageId, request.getQuantity());
    }
}
