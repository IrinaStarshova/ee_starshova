package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
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

    @Override
    @Transactional
    public Flower setQuantityInCart(Long id, int quantity) {
        Flower flower=entityManager.getReference(Flower.class,id);
        flower.setQuantityInCart(flower.getQuantityInCart()+quantity);
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
        TypedQuery<Flower> q = entityManager.createQuery("Select f from  Flower f", Flower.class);
        return q.getResultList();
    }
    @Override
    public List<Flower> findFlowers(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Flower> q = entityManager.createQuery
                ("Select f from  Flower f where upper(f.name) like upper(:name) " +
                                "and f.price between :minPrice and :maxPrice", Flower.class);
        q.setParameter("name", "%"+name+"%");
        q.setParameter("minPrice", minPrice);
        q.setParameter("maxPrice", maxPrice);
        return q.getResultList();
    }
}
