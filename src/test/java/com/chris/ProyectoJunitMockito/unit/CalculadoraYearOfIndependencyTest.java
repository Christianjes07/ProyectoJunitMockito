package com.chris.ProyectoJunitMockito.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chris.ProyectoJunitMockito.util.DiferenciaEntreFechas;

public class CalculadoraYearOfIndependencyTest {

    @Autowired
    private DiferenciaEntreFechas diferenciaEntreFechas;

    @Test
    void CalculadoraYearOfIndependency() {

        diferenciaEntreFechas = new DiferenciaEntreFechas();

        LocalDate today = LocalDate.now();
        String fechaIndependencia = "16/07/1699";

        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);
        System.out.println(resultado.getDays());
        System.out.println(resultado.getMonths());
        System.out.println(resultado.getYears());

    }

    @Test
    void testSuma() {
        int resultado = 2 + 2;
        assertEquals(5, resultado); // Esto va a fallar
    }

}
