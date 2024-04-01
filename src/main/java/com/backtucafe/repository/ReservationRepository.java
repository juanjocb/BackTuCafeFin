package com.backtucafe.repository;

import com.backtucafe.model.Business;
import com.backtucafe.model.Client;
import com.backtucafe.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByClientId(Client clientId);

    List<Reservation> findAllByBusinessId(Business businessId);
}
