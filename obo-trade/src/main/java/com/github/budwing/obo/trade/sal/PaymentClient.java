package com.github.budwing.obo.trade.sal;

import com.github.budwing.obo.trade.dto.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("obo-payment")
public interface PaymentClient {
    @PostMapping("/obo/payment")
    String pay(@RequestBody PayRequest payRequest);
}
