package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.StorageClient;
import cn.zchengb.order.infrastructure.client.request.StockDeductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("storage:8082")
public interface StorageFeignClient extends StorageClient {
    @Override
    @GetMapping("/storages/{stock-id}/latestPrice")
    long fetchLatestPrice(@PathVariable("stock-id") long stockId);

    @PostMapping("/storages/{stock-id}/deduction")
    void deductStock(@PathVariable("stock-id") long stockId, @RequestBody StockDeductRequest request);

    @Override
    default void deductStock(long stockId, int quantity) {
        deductStock(stockId, new StockDeductRequest(quantity));
    }
}
