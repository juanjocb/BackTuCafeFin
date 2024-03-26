package com.backtucafe.repository;

import com.backtucafe.model.Admin;
import com.backtucafe.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
    Admin findById(long id);
    List<Admin> findAll();

}
