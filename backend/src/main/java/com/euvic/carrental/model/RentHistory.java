package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "rents_history")
@Entity
public class RentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateFrom;

    @Column(nullable = false)
    private LocalDateTime dateTo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Car car;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private ParkingHistory parkingHistoryFrom;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private ParkingHistory parkingHistoryTo;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isAccepted;

    @Column()
    private String reasonForTheLoan;

    @Column()
    private String adminResponseForTheRequest;

    @Column()
    private String faultMessage;

    public RentHistory() {

    }

    public RentHistory(final Long id, final User user, final Car car, final LocalDateTime dateFrom, final LocalDateTime dateTo, final ParkingHistory parkingHistoryFrom
            , final ParkingHistory parkingHistoryTo, final Boolean isActive, final Boolean isAccepted, final String reasonForTheLoan, final String adminResponseForTheRequest, final String faultMessage) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingHistoryFrom = parkingHistoryFrom;
        this.parkingHistoryTo = parkingHistoryTo;
        this.isActive = isActive;
        this.isAccepted = isAccepted;
        this.adminResponseForTheRequest = adminResponseForTheRequest;
        this.reasonForTheLoan = reasonForTheLoan;
        this.faultMessage = faultMessage;
    }
}
