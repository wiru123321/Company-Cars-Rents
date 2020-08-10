package com.euvic.carrental.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentListCarByTime {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
