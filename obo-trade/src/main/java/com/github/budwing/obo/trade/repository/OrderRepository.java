package com.github.budwing.obo.trade.repository;

import com.github.budwing.obo.trade.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
