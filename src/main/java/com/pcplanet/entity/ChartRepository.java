package com.pcplanet.entity;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(Integer userId);
}
