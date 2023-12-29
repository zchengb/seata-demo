package cn.zchengb.order.domain;

public interface StorageClient {
    long fetchLatestPrice(long stockId);

    void deductStock(long stockId, int quantity);
}
