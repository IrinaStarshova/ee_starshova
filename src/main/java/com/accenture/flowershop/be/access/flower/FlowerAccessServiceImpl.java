package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.repositories.FlowerRepository;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flower.QFlower;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс доступа к базе данных для цветов
 */

@Repository
public class FlowerAccessServiceImpl implements FlowerAccessService {

    @Autowired
    private FlowerRepository repository;

    @Override
    public List<Flower> getFlowers() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public List<Flower> findFlowers(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!name.isEmpty())
            builder.and(QFlower.flower.name.containsIgnoreCase(name));
        if (minPrice != null)
            builder.and(QFlower.flower.price.goe(minPrice));
        if (maxPrice != null)
            builder.and(QFlower.flower.price.loe(maxPrice));
        return Lists.newArrayList
                (repository.findAll(builder.getValue()));
    }

    @Override
    public Flower getFlower(Long id) {
        return repository.findOne(QFlower.flower.id.eq(id)).orElse(null);
    }
}
