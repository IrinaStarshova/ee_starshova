package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.repositories.FlowerRepository;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flower.QFlower;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс доступа к базе данных для цветов
 */
@Repository
public class FlowerAccessServiceImpl implements FlowerAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private FlowerRepository repository;

    @Override
    @Transactional
    public Flower setQuantityInCart(Long id, int quantity) {
        Flower flower=entityManager.getReference(Flower.class,id);
        flower.setQuantityInCart(flower.getQuantityInCart()+quantity);
        return flower;
    }

    @Override
    @Transactional
    public Flower setQuantity(Long id, int quantity) {
        Flower flower=entityManager.getReference(Flower.class,id);
        flower.setQuantity(flower.getQuantity()-quantity);
        flower.setQuantityInCart(flower.getQuantityInCart() - quantity);
        return flower;
    }

    @Override
    @Transactional
    public void increaseQuantityOfFlowers(int count){
        for(Flower f: getFlowers())
            f.setQuantity(f.getQuantity()+count);
    }

    @Override
    public List<Flower> getFlowers() {
        return Lists.newArrayList(repository.findAll());
    }
    @Override
    public List<Flower> findFlowers(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        BooleanBuilder builder=new BooleanBuilder();
        if(!name.isEmpty())
            builder.and(QFlower.flower.name.containsIgnoreCase(name));
        if(minPrice != null)
            builder.and(QFlower.flower.price.goe(minPrice));
        if(maxPrice != null)
            builder.and(QFlower.flower.price.loe(maxPrice));
        return Lists.newArrayList
                (repository.findAll(builder.getValue()));
    }
}
