package cn.zchengb.account.domain;

import java.util.Optional;

public interface AccountRepository {
    void save(Account account);

    Optional<Account> findById(Long accountId);
}
