package com.accenture.flowershop.be.access.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
import org.springframework.data.querydsl.*;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable>
        extends Repository<T,ID>, QuerydslPredicateExecutor<T>{
}
