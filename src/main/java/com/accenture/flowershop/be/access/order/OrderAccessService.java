package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

/**
 * Интерфейс доступа к базе данных для заказов
 */
public interface OrderAccessService {

    /**
     * Метод для получения заказа с определенным идентификатором
     *
     * @param orderId - идентификатор заказа
     * @return сущность заказа с определенным идентификатором
     */
    Order getOrder(Long orderId);

    /**
     * Метод для получения всех имеющихся заказов
     *
     * @return список имеющихся заказов
     */
    List<Order> getOrders();

    /**
     * Метод для удаления заказа
     *
     * @param order - заказ, который требуется удалить
     */
    void deleteOrder(Order order);

}
