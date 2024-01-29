package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

public interface PromoCodeRepository extends JpaRepository<PromoCode, UUID> {


    @Modifying
    @Transactional
    @Query("UPDATE PromoCode p SET p.code = ?1, p.discount = ?2, p.active = ?3, p.expirationDate = ?4 WHERE p.id = ?5")
    void update(String code, Integer discount, boolean active, Date expirationDate ,UUID id);
}
