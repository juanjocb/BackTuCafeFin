package com.backtucafe.repository;

import com.backtucafe.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByEmail(String email);
    Business findByName(String name);
}
