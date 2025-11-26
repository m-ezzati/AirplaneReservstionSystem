package com.mycompany.model;

import com.mycompany.model.base.BaseEntity;
import com.mycompany.model.enums.FlightType;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Flight extends BaseEntity {

//    @ManyToOne
//    private Airline airline;

    private String airlineName;
    private String flightNumber;
    private String origin;
    private String destination;

    private Double price;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @NotNull
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    private FlightType flightType;

}
