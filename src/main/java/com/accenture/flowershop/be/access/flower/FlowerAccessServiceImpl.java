package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.List;

/**
 * Класс доступа к базе данных для цветов
 */
@Service
public class FlowerAccessServiceImpl implements FlowerAccessService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateFlower(Flower flower) {
        entityManager.merge(flower);
    }

    @Override
    public Flower getFlower(Long flowerId) {
        return entityManager.find(Flower.class, flowerId);
    }

    @Override
    public List<Flower> getFlowers() {
        TypedQuery<Flower> q = entityManager.createQuery("Select f from  Flower f", Flower.class);
        return q.getResultList();
    }
}
