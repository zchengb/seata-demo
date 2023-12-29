package cn.zchengb.account.infrastructure;

import cn.zchengb.account.domain.Account;
import cn.zchengb.account.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Long>, AccountRepository {
}
