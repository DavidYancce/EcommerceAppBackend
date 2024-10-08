package com.ecommerceproject.adapter.out;

import com.ecommerceproject.domain.model.User;
import com.ecommerceproject.domain.port.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Integer> {

}
