package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.StorageClient;
import cn.zchengb.order.infrastructure.client.request.StorageDeductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class StorageDelegatedFeignClient implements StorageClient {
    private final StorageFeignClient storageFeignClient;

    @Override
    public long fetchLatestPrice(long storageId) {
        return storageFeignClient.fetchLatestPrice(storageId);
    }

    @Override
    public void deductStorage(long storageId, int quantity) {
        storageFeignClient.deductStorage(storageId, new StorageDeductRequest(quantity));
    }

    @FeignClient("storage:8082")
    interface StorageFeignClient {
        @GetMapping("/storages/{storage-id}/latestPrice")
        long fetchLatestPrice(@PathVariable("storage-id") long storageId);

        @PostMapping("/storages/{storage-id}/deduction")
        void deductStorage(@PathVariable("storage-id") long storageId, @RequestBody StorageDeductRequest request);
    }
}
