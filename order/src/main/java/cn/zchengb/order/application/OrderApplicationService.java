package cn.zchengb.order.application;

import cn.zchengb.order.domain.AccountClient;
import cn.zchengb.order.domain.Order;
import cn.zchengb.order.domain.OrderRepository;
import cn.zchengb.order.domain.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {
    private final StorageClient storageClient;
    private final AccountClient accountClient;
    private final OrderRepository orderRepository;

    public void createOrder(long accountId, long storageId, int quantity) {
        var price = storageClient.fetchLatestPrice(storageId);
        price = price * quantity;
        accountClient.deductBalance(accountId, price);
        storageClient.deductStock(storageId, quantity);

        var order = Order.create(accountId, storageId, price, quantity);
        orderRepository.save(order);
    }
}
