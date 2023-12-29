package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.StorageClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("storage:8082")
public interface StorageFeignClient extends StorageClient {
    @Override
    @GetMapping("/storages/{stock-id}/latestPrice")
    long fetchLatestPrice(@PathVariable("stock-id") long stockId);
}
