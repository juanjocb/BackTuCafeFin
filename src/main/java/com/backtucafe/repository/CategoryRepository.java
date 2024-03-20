package com.backtucafe.repository;

import com.backtucafe.model.Business;
import com.backtucafe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
