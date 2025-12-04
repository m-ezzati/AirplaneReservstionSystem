package com.mycompany.model;

import com.mycompany.model.base.BaseEntity;
import com.mycompany.model.enums.FlightType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    private String airlineName;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Integer totalSeat;

    @Builder.Default
    @Column(columnDefinition = "int default 0")
    private Integer reservedSeats = 0;


    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;

}
