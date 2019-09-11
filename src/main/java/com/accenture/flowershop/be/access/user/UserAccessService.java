package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;

/**
 * Интерфейс доступа к базе данных для пользователей
 */
public interface UserAccessService {

    /**
     * Метод для поиска пользователя по логину и паролю
     * @param login - login пользователя
     * @param password - пароль пользователя
     * @return сущность пользователя
     */
    User findUser(String login, String password);

    /**
     * Метод для получения определенного пользователя
     * @param login - login пользователя
     * @return сущность пользователя с определенным login
     */
    User getUser(String login);

    /**
     * Метод для добавления нового пользователя
     * @param user - пользователь, которого необходимо добавить
     */

    void addUser(Customer user);

    /**
     * Метод для обновления сущности покупателя
     * @param customer - сущность покупателя для обновления
     */
    void updateCustomer(Customer customer);
}
