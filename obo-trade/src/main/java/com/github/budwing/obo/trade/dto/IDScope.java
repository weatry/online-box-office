package com.github.budwing.obo.trade.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Data
@ToString
@Slf4j
public class IDScope {
    public static final int UPPER_BOUND = 99999999;
    private Long start;
    private Long end;

    private Random random = new Random(System.currentTimeMillis());

    public boolean needRefresh() {
        return start.equals(end);
    }

    public Long nextID() {
        return start++;
    }

    //TODO: this method should listen new round event and change its algorithm dynamically
    public String generatePickupCode() {
        StringBuilder sb = new StringBuilder();
        Long id = nextID();
        int r = random.nextInt(UPPER_BOUND);
        log.debug("id: {}, random: {},", id, r);
        for (int i=0; i<4; i++) {
            sb.append(r%10); r/=10;
            sb.append(r%10); r/=10;
            sb.append(id%10); id/=10;
            sb.append(id%10); id/=10;
        }
        log.debug("generate pickup code: {}", sb);

        return sb.toString();
    }
}
