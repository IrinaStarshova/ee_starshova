package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;

/**
 * Интерфейс логики для заказов
 */
public interface OrderBusinessService {

    /**
     * Метод для создания нового заказа
     *
     * @param login - login пользователя, заказ которого должен быть создан
     */
    void createNewOrder(String login)
            throws ObjectOptimisticLockingFailureException,
            UnavailableQuantityException;

    /**
     * Метод для получения списка заказов
     *
     * @return список заказов
     */
    List<Order> getOrders();

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
    void deleteOrder(Long id, String login)
            throws ObjectOptimisticLockingFailureException;
}