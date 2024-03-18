package com.backtucafe.repository;

import com.backtucafe.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findById(long id);

}
