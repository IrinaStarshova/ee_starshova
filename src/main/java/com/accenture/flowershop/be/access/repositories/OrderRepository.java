package com.accenture.flowershop.be.access.repositories;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order,Long> {
}
