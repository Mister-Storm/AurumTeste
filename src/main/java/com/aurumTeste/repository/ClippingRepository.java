package com.aurumTeste.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurumTeste.model.Clipping;

@Repository
public interface ClippingRepository extends JpaRepository<Clipping, Long>{

    @Override
    Page<Clipping> findAll(Pageable pageable);
}
