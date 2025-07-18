package com.chris.ProyectoJunitMockito.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DiferenciaEntreFechas {

    public Period calculateYearsOfIndependency(String independenceDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        LocalDate localDate = LocalDate.parse(independenceDay, formatter);
        LocalDate today = LocalDate.now();
        return Period.between(localDate, today);
    }
}