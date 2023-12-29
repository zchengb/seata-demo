package cn.zchengb.order.domain;

public interface StorageClient {
    long fetchLatestPrice(long stockId);
}
