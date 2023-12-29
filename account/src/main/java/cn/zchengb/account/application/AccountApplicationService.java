package cn.zchengb.account.application;

import cn.zchengb.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountApplicationService {
    private final AccountRepository accountRepository;

    public void balanceDeduct(Long accountId, long deductValue) {
        if (deductValue <= 0) {
            throw new IllegalArgumentException("deduct value must be positive.");
        }

        var account = accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("cannot found account")
        );

        account.deduct(deductValue);
        accountRepository.save(account);
    }
}
