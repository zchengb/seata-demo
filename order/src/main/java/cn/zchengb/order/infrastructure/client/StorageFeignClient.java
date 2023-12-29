package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.StorageClient;
import cn.zchengb.order.infrastructure.client.request.StorageDeductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("storage:8082")
public interface StorageFeignClient extends StorageClient {
    @Override
    @GetMapping("/storages/{storage-id}/latestPrice")
    long fetchLatestPrice(@PathVariable("storage-id") long storageId);

    @PostMapping("/storages/{storage-id}/deduction")
    void deductStorage(@PathVariable("storage-id") long storageId, @RequestBody StorageDeductRequest request);

    @Override
    default void deductStock(long storageId, int quantity) {
        deductStorage(storageId, new StorageDeductRequest(quantity));
    }
}
