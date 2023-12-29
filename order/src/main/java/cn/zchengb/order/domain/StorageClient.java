package cn.zchengb.order.domain;

public interface StorageClient {
    long fetchLatestPrice(long storageId);

    void deductStorage(long storageId, int quantity);
}
