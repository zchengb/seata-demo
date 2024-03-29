package cn.zchengb.order.application;

import cn.zchengb.order.domain.AccountClient;
import cn.zchengb.order.domain.Order;
import cn.zchengb.order.domain.OrderRepository;
import cn.zchengb.order.domain.StorageClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApplicationService {
    private final StorageClient storageClient;
    private final AccountClient accountClient;
    private final OrderRepository orderRepository;

    @GlobalTransactional
    public void createOrder(long accountId, long storageId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive.");
        }

        var price = storageClient.fetchLatestPrice(storageId);
        price = price * quantity;
        accountClient.deductBalance(accountId, price);
        storageClient.deductStorage(storageId, quantity);

        var order = Order.create(accountId, storageId, price, quantity);
        orderRepository.save(order);
    }
}
