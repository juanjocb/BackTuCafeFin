package com.backtucafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Networks")
public class Networks {

    private Long id_networks;
    private String facebook;
    private String instagram;
    private String whatssap;

}
