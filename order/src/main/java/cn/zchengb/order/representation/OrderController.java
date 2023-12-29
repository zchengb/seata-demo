package cn.zchengb.order.representation;

import cn.zchengb.order.application.OrderApplicationService;
import cn.zchengb.order.representation.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @PostMapping("/orders")
    public void createOrder(@RequestBody CreateOrderRequest request) {
        orderApplicationService.createOrder(request.getAccountId(), request.getStockId(), request.getQuantity());
    }
}
