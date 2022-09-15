package com.github.budwing.obo.trade;

import com.github.budwing.obo.trade.dto.IDScope;
import com.github.budwing.obo.trade.sal.IDCenterClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestIDCenter {
    private IDScope idScope;
    @Autowired
    private IDCenterClient idCenterClient;

    @Test
    @Repeat(1010)
    public void testID() {
        log.debug("test id center");

        if (idScope==null || idScope.needRefresh()) {
            log.debug("refresh id scope {}", idScope);
            idScope = idCenterClient.getNext(1);
        }
        log.debug("next pickup code {}", idScope.generatePickupCode());
    }
}
