package com.exchangeproject.adapter.out;

import com.exchangeproject.domain.model.User;
import com.exchangeproject.domain.port.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Integer> {

}
