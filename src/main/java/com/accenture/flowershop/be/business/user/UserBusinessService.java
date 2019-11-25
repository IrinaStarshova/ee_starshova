package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.business.exceptions.CreateUserException;
import com.accenture.flowershop.be.entity.user.User;

/**
 * Интерфейс логики для пользователей
 */
public interface UserBusinessService {

    /**
     * Метод для создания нового пользователя
     *
     * @param username    - login пользователя
     * @param password    - пароль пользователя
     * @param firstName   - имя пользователя
     * @param patronymic  - отчество пользователя
     * @param lastName    - фамилия пользователя
     * @param address     - адрес пользователя
     * @param phoneNumber - номер телефона пользователя
     */
    void createNewUser(String username, String password,
                       String firstName, String patronymic,
                       String lastName, String address, String phoneNumber)
            throws CreateUserException;

    /**
     * Метод для определения существует ли пользователь с определенным логином
     *
     * @param login - login пользователя
     * @return булево значение, указывающее существует ли пользователь с данным логином
     */
    boolean isUserExists(String login);

    /**
     * Метод для осуществления входа пользователя
     *
     * @param login    - login пользователя
     * @param password - пароль пользователя
     * @return сущность пользователя при успешном входе
     */
    User userLogin(String login, String password);

    /**
     * Метод для изменения размера скидки пользователя
     *
     * @param login    - login пользователя
     * @param discount - новое значение скидки пользователя
     */
    void changeDiscount(String login, int discount);

    /**
     * Метод для получения пользователя с определенным логином
     *
     * @param login - login пользователя
     * @return сущность пользователя
     */
    User getCustomer(String login);
}