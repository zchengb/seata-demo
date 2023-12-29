package cn.zchengb.order.infrastructure;

import cn.zchengb.order.domain.Order;
import cn.zchengb.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderRepository {
}
