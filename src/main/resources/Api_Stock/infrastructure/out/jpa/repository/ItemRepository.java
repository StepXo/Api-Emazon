package com.BootcampPragma.Api_Stock.infrastructure.out.jpa.repository;

import com.BootcampPragma.Api_Stock.infrastructure.out.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository <ItemEntity, Long> {
    Optional<ItemEntity> findByName(String name);
    void deleteByName(String name);
}
