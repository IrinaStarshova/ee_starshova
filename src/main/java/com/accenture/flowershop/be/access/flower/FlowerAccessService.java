package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс доступа к базе данных для цветов
 */
public interface FlowerAccessService {

    /**
     * Метод для получения всех имеющихся цветов
     *
     * @return список имеющихся цветов
     */
    List<Flower> getFlowers();

    /**
     * Метод для поиска цветов по имени и диапазону цен
     *
     * @param name      - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo   - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<Flower> findFlowers(String name, BigDecimal priceFrom, BigDecimal priceTo);

    /**
     * Метод для получения определенного цветка
     *
     * @param id      - идентификатор цветка
     * @return сущность цветка с указанным идентификатором
     */
    Flower getFlower(Long id);

    void update();
}
