package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.AccountClient;
import cn.zchengb.order.infrastructure.client.request.DeductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account:8080")
public interface AccountFeignClient extends AccountClient {
    @Override
    default void deductBalance(long accountId, long price) {
        deductBalance(accountId, new DeductRequest(price));
    }

    @PostMapping("/accounts/{account-id}/balance/deduction")
    void deductBalance(@PathVariable("account-id") long accountId, @RequestBody DeductRequest request);
}
