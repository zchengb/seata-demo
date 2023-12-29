package cn.zchengb.account.representation;

import cn.zchengb.account.application.AccountApplicationService;
import cn.zchengb.account.representation.request.DeductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountApplicationService accountApplicationService;
    @Value("${test.value:test}")
    private String value;

    @PostMapping("/accounts/{account-id}/balance/deduction")
    public void balanceDeduct(@PathVariable("account-id") Long accountId, @RequestBody DeductRequest request) {
        accountApplicationService.balanceDeduct(accountId, request.getDeductValue());
    }

    @GetMapping("/account")
    public String getValue() {
        return value;
    }
}
