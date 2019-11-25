package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.business.exceptions.CreateUserException;
import com.accenture.flowershop.be.entity.user.User;


/**
 * Интерфейс доступа к базе данных для пользователей
 */
public interface UserAccessService {

    /**
     * Метод для получения определенного пользователя
     *
     * @param login - login пользователя
     * @return сущность пользователя с определенным login
     */
    User getUser(String login);

    User getCustomer(String login);

    /**
     * Метод для добавления нового пользователя
     *
     * @param user - пользователь, которого необходимо добавить
     */
    void addUser(User user) throws CreateUserException;

}
