package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.data.domain.Page;

/**
 * Интерфейс логики для заказов
 */
public interface OrderBusinessService {

    /**
     * Метод для создания нового заказа
     *
     * @param login - login пользователя, заказ которого должен быть создан
     */
    void createNewOrder(String login);

    Page<Order> getOrders(int pageNumber, int quantity);

    /**
     * Метод для оплаты заказа
     *
     * @param orderId - идентификатор заказа, котороый требуется оплатить
     * @return булево значение, указывающее произошла ли оплата
     */
    boolean payOrder(Long orderId);

    /**
     * Метод для закрытия заказа
     *
     * @param id - идентификатор заказа, который требуется закрыть
     */
    void closeOrder(Long id);

    /**
     * Метод для удаления заказа
     *
     * @param id - идентификатор заказа, который требуется удалить
     */
    void deleteOrder(Long id);
}