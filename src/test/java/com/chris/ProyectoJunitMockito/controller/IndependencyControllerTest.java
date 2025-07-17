package com.chris.ProyectoJunitMockito.controller;

import com.chris.ProyectoJunitMockito.models.Country;
import com.chris.ProyectoJunitMockito.models.CountryResponse;
import com.chris.ProyectoJunitMockito.repositories.CountryRepository;
import com.chris.ProyectoJunitMockito.util.DiferenciaEntreFechas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

public class IndependencyControllerTest {

    private CountryRepository countryRepositoryMock;
    private IndependencyController independencyController;

    @BeforeEach
    void setUp() {
        // Crear mock del repositorio
        countryRepositoryMock = Mockito.mock(CountryRepository.class);
        DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

        // Crear instancia del controlador con el mock y utilidad real
        independencyController = new IndependencyController(countryRepositoryMock, diferenciaEntreFechas);

        // Simulación del país
        Country mockCountry = new Country();
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryIdependenceDate("27/02/1844");
        mockCountry.setCountryId(1L);
        mockCountry.setCountryName("Republica Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");

        // Configurar comportamiento del mock
        Mockito.when(countryRepositoryMock.findCountryByIsoCode("DO")).thenReturn(mockCountry);
    }

    @Test
    void getCountryDetails_ShouldReturnCorrectCountryResponse() {
        // Ejecutar el método del controlador
        ResponseEntity<CountryResponse> responseEntity = independencyController.getCountryDetails("DO");

        // Verificar que la respuesta no sea nula
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

        CountryResponse response = responseEntity.getBody();

        // Verificar que los campos del DTO sean correctos
        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getCountryName()).isEqualTo("Republica Dominicana");
    }
}
