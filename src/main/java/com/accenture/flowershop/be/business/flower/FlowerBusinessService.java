package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

/**
 * Интерфейс логики для цветов
 */
public interface FlowerBusinessService {

    /**
     * Метод для получения списка имеющихся цветов
     *
     * @return список цветов
     */
    List<Flower> getFlowers();

    /**
     * Метод для увеличения значения количества определенного вида цветка
     *
     * @param flower   - идентификатор цветка
     * @param quantity - число, на которое необходимо увеличить количество цветов
     */
    void increaseQuantityOfFlower(Flower flower, int quantity);

    /**
     * Метод для уменьшения значения количества определенного вида цветка
     *
     * @param flower   - идентификатор цветка
     * @param quantity - число, на которое необходимо уменьшить количество цветов
     */
    void decreaseQuantityOfFlower(Flower flower, int quantity);

    /**
     * Метод для увеличения значения количества всех цветов на определенное значение
     *
     * @param count - число, на которое должно быть увеличено количество
     */
    void increaseQuantityOfAllFlowers(int count);

    /**
     * Метод для поиска цветов по имени и диапазону цен
     *
     * @param name      - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo   - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<Flower> findFlowers(String name, String priceFrom, String priceTo);
}