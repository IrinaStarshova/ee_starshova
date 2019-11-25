package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.data.domain.Page;

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

    Page<Order> getOrders(int pageNumber, int quantity);

    /**
     * Метод для удаления заказа
     *
     * @param order - заказ, который требуется удалить
     */
    void deleteOrder(Order order);

}
