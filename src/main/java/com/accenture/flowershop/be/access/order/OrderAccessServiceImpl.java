package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.access.repositories.OrderRepository;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.QOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Класс доступа к базе данных для заказов
 */
@Repository
public class OrderAccessServiceImpl implements OrderAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private OrderRepository repository;

    @Override
    public Order getOrder(Long orderId) {
        return repository.findOne(QOrder.order.id.eq(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found by id"));
    }

    @Override
    public Page<Order> getOrders(int pageNumber, int quantity) {
        Sort creationDateAndStatusSort = new Sort(Sort.Direction.DESC
                , "creationDate", "status");
        return repository.findAll(QOrder.order.isNotNull(), PageRequest.of(pageNumber, quantity,
                creationDateAndStatusSort));
    }

    @Override
    public void deleteOrder(Order order) {
        entityManager.remove(order);
    }
}
