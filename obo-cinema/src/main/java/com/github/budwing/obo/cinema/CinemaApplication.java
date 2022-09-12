package com.github.budwing.obo.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CinemaApplication {
    /*@Bean
    public GlobalTransactionalInterceptor globalTransactionalInterceptor() {
        return new GlobalTransactionalInterceptor(new DefaultFailureHandlerImpl());
    }

    @Bean
    public Advisor seataAdviceAdvisor(GlobalTransactionalInterceptor globalTransactionalInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(io.seata.spring.annotation.GlobalTransactional)");
        return new DefaultPointcutAdvisor(pointcut, globalTransactionalInterceptor);
    }*/
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }
}
