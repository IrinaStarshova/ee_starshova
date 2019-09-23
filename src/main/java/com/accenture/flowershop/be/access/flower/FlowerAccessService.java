package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс доступа к базе данных для цветов
 */
public interface FlowerAccessService {

    /**
     * Метод для установки значения количества данных цветов в корзине
     * @param id - идентификатор цветка
     * @param quantity - количество добавленных в корзину цветов данного вида
     * @return сущность цветка, в которую были внесены изменения
     */
    Flower setQuantityInCart(Long id, int quantity);

    Flower setQuantity(Long id, int quantity);

    /**
     * Метод для увеличения значения количества всех цветов на определенное значение
     * @param count - число, на которое должно быть увеличено количество
     */
    void increaseQuantityOfFlowers(int count);

    /**
     * Метод для получения всех имеющихся цветов
     * @return список имеющихся цветов
     */
    List<Flower> getFlowers();

    /**
     * Метод для поиска цветов по имени и диапазону цен
     * @param name - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<Flower> findFlowers(String name, BigDecimal priceFrom, BigDecimal priceTo);
}
