package com.accenture.flowershop.be.access.repositories;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends BaseRepository<Flower,Long> {
}
