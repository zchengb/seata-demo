package cn.zchengb.order.application;

import cn.zchengb.order.domain.AccountClient;
import cn.zchengb.order.domain.Order;
import cn.zchengb.order.domain.OrderRepository;
import cn.zchengb.order.domain.StorageClient;
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

    public void createOrder(long accountId, long storageId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive.");
        }

        var price = storageClient.fetchLatestPrice(storageId);
        log.info("get latest price: {}", price);
        price = price * quantity;
        accountClient.deductBalance(accountId, price);
        storageClient.deductStorage(storageId, quantity);
        log.info("deduct balance: {}, deduct storage: {}", price, quantity);

        var order = Order.create(accountId, storageId, price, quantity);
        log.info("create order: {}", order);
        orderRepository.save(order);
    }
}
