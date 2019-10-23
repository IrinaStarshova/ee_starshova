package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.access.repositories.OrderRepository;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.QOrder;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Класс доступа к базе данных для заказов
 */
@Repository
public class OrderAccessServiceImpl implements OrderAccessService {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private OrderRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(OrderAccessService.class);

    @Override
    public Order getOrder(Long orderId) {
        return repository.findOne(QOrder.order.id.eq(orderId)).orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        Sort creationDateAndStatusSort = new Sort(Sort.Direction.ASC
                , "creationDate", "status");
        return Lists.newArrayList(repository.findAll(QOrder.order.isNotNull(), creationDateAndStatusSort));
    }

    @Override
    public void deleteOrder(Order order) {
        entityManager.remove(order);
    }
}
