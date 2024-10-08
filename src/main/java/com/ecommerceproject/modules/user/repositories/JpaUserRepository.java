package com.ecommerceproject.modules.user.repositories;

import com.ecommerceproject.modules.user.entity.User;
import com.ecommerceproject.modules.user.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Integer> {

}
