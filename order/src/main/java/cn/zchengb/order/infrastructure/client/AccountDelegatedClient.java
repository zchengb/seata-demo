package cn.zchengb.order.infrastructure.client;

import cn.zchengb.order.domain.AccountClient;
import cn.zchengb.order.infrastructure.client.request.BalanceDeductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class AccountDelegatedClient implements AccountClient {
    private final AccountFeignClient accountFeignClient;

    @Override
    public void deductBalance(long accountId, long price) {
        accountFeignClient.deductBalance(accountId, new BalanceDeductRequest(price));
    }

    @FeignClient("account:8080")
    interface AccountFeignClient {
        @PostMapping("/accounts/{account-id}/balance/deduction")
        void deductBalance(@PathVariable("account-id") long accountId, @RequestBody BalanceDeductRequest request);
    }
}
