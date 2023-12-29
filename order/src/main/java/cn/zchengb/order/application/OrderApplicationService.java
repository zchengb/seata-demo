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

    public void createOrder(long accountId, long stockId, int quantity) {
        var price = storageClient.fetchLatestPrice(stockId);
        price = price * quantity;
        accountClient.deductBalance(accountId, price);
        storageClient.deductStock(stockId, quantity);

        var order = Order.create(accountId, stockId, price, quantity);
        orderRepository.save(order);
    }
}
