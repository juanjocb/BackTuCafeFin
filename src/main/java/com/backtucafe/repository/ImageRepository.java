package com.backtucafe.repository;

import com.backtucafe.model.Business;
import com.backtucafe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository  extends JpaRepository<Image, Long> {

    List<Image> findByBusiness(Business business);
}
