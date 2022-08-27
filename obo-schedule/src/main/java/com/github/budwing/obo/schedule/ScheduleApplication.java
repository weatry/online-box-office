package com.github.budwing.obo.schedule;

import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScheduleApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        //loadSentinelRules();
        SpringApplication.run(ScheduleApplication.class, args);
    }

    private static void loadSentinelRules() {
        List<DegradeRule> rules = new ArrayList<>();
        ContextUtil.enter("create-order", "obo-trade");
        DegradeRule rule = new DegradeRule("GET:http://obo-cinema/obo/cinema/{cinemaId}/hall/{hallId}/seat")
                .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT)
                // Max allowed response time
                .setCount(5)
                // Retry timeout (in second)
                .setTimeWindow(10)
                .setStatIntervalMs(20000);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
    }
}
