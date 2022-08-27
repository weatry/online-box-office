package com.github.budwing.obo.trade.sal;

import com.github.budwing.obo.trade.dto.PayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "obo-payment", fallbackFactory = PaymentClientFallbackFactory.class)
public interface PaymentClient {
    @PostMapping("/obo/payment")
    String pay(@RequestBody PayRequest payRequest);
}

@Component
@Slf4j
class PaymentClientFallbackFactory implements FallbackFactory<PaymentClient> {
    @Override
    public PaymentClient create(Throwable cause) {
        log.error("Payment client fallback for exception:{}", cause.getClass());
        log.error("Payment client exception message:{}", cause.getMessage());
        return new PaymentClient() {
            @Override
            public String pay(PayRequest payRequest) {
                throw new RuntimeException(cause);
            }
        };
    }
}
