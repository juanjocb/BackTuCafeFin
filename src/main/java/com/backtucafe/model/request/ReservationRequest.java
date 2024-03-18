package com.backtucafe.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @NotNull
    String email;

    String name;

    @NotNull
    @Future(message = "La fecha de la reserva debe ser en el futuro")
    LocalDate date;

    @NotNull
    @Future(message = "La hora de la reserva debe ser en el futuro")
    LocalTime hour;

    String description;
}
