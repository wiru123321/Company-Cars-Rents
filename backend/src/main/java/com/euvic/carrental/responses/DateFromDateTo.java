package com.euvic.carrental.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateFromDateTo {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
