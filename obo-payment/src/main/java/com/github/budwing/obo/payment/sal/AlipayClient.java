package com.github.budwing.obo.payment.sal;

import com.github.budwing.obo.payment.dto.PayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AlipayClient {
    public String pay(PayRequest payRequest) {
        log.debug("Invoke Alipay for request: {}", payRequest);
        String id = UUID.randomUUID().toString();
        log.debug("Invocation of Alipay is mocked, mocked id is {}.", id);
        return id;
    }
}
