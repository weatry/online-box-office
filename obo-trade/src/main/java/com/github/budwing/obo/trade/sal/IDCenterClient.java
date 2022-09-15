package com.github.budwing.obo.trade.sal;

import com.github.budwing.obo.trade.dto.IDScope;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "obo-id-center")
public interface IDCenterClient {
    @GetMapping("/obo/id/{id}")
    IDScope getNext(@PathVariable("id") Integer id);
}

class IDCenterClientFallbackFactory implements FallbackFactory<IDCenterClient> {
    @Override
    public IDCenterClient create(Throwable cause) {
        return new IDCenterClient() {
            @Override
            public IDScope getNext(Integer id) {
                throw new RuntimeException(cause);
            }
        };
    }
}
