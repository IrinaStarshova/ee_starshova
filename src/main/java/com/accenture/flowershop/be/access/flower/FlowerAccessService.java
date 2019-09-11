package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import java.util.List;

/**
 * Интерфейс доступа к базе данных для цветов
 */
public interface FlowerAccessService {

    /**
     * Метод для обновления сущности цветка
     * @param flower - сущность цветка для обновления
     */
    void updateFlower(Flower flower);

    /**
     * Метод для получения определенного цветка
     * @param flowerId - идектификатор необходимого цветка
     * @return сущность цветка
     */
    Flower getFlower(Long flowerId);

    /**
     * Метод для получения всех имеющихся цветов
     * @return список имеющихся цветов
     */
    List<Flower> getFlowers();
}
