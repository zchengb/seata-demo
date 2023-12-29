package cn.zchengb.order.domain;

public interface StorageClient {
    long fetchLatestPrice(long storageId);

    void deductStock(long storageId, int quantity);
}
