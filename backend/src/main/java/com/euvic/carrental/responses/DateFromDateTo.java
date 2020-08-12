package com.euvic.carrental.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateFromDateTo {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public DateFromDateTo() {
    }

    public DateFromDateTo(final LocalDateTime dateFrom, final LocalDateTime dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
