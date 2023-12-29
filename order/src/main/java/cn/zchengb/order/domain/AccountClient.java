package cn.zchengb.order.domain;

public interface AccountClient {
    void deductBalance(long accountId, long price);
}
