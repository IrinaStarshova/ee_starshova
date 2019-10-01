package com.accenture.flowershop.be.access.repositories;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,String> {
}
