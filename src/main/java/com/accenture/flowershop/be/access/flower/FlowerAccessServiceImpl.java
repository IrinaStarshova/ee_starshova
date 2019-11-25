package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.repositories.FlowerRepository;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flower.QFlower;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс доступа к базе данных для цветов
 */

@Repository
public class FlowerAccessServiceImpl implements FlowerAccessService {

    @Autowired
    private FlowerRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flower> getFlowers() {
        return (List<Flower>) repository.findAll();
    }

    @Override
    public List<Flower> findFlowers(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!name.isEmpty()) {
            builder.and(QFlower.flower.name.containsIgnoreCase(name));
        }
        if (minPrice != null) {
            builder.and(QFlower.flower.price.goe(minPrice));
        }
        if (maxPrice != null) {
            builder.and(QFlower.flower.price.loe(maxPrice));
        }
        return (List<Flower>) repository.findAll(builder.getValue());
    }

    @Override
    public Flower getFlower(Long id) {
        return repository.findOne(QFlower.flower.id.eq(id))
                .orElseThrow(() -> new RuntimeException("Flower not found by id"));
    }

    @Override
    @Transactional
    public void update() {
        entityManager.flush();
    }
}
