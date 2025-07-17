package com.chris.ProyectoJunitMockito.controller;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.chris.ProyectoJunitMockito.models.Country;
import com.chris.ProyectoJunitMockito.repositories.CountryRepository;
import com.chris.ProyectoJunitMockito.util.DiferenciaEntreFechas;

public class GetAllCountriesControllerTest {

    private CountryRepository countryRepositoryMock;
    private DiferenciaEntreFechas diferenciaEntreFechas;
    private IndependencyController independencyController;

    @BeforeEach
    void setUp() {

        countryRepositoryMock = Mockito.mock(CountryRepository.class);
        diferenciaEntreFechas = new DiferenciaEntreFechas();
        independencyController = new IndependencyController(countryRepositoryMock, diferenciaEntreFechas);

    }

    @Test
    void getAllCountries_ShouldReturnListOfCountries() {

        // simular la lista

        Country paises = new Country();

        paises.setCountryId(1L);
        paises.setCountryName("Mexico");
        paises.setCountryCapital("Mx");
        paises.setIsoCode("MX");
        paises.setCountryIdependenceDate("16/09/1810");

        Country paises2 = new Country();

        paises2.setCountryId(2L);
        paises2.setCountryName("Canada");
        paises2.setCountryCapital("Canada");
        paises2.setIsoCode("CAN");
        paises2.setCountryIdependenceDate("16/09/1810");

        List<Country> countriesMocked = Arrays.asList(paises, paises2);

        Mockito.when(countryRepositoryMock.findAll()).thenReturn(countriesMocked);

        ResponseEntity<List<Country>> response = independencyController.getAllCountries();

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody()).hasSize(2);
        Assertions.assertThat(response.getBody().get(0).getCountryName()).isEqualTo("Mexico");
    }

}
