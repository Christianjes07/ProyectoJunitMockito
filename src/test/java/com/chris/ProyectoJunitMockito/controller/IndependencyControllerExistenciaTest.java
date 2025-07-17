package com.chris.ProyectoJunitMockito.controller;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chris.ProyectoJunitMockito.repositories.CountryRepository;

@ExtendWith(MockitoExtension.class)
public class IndependencyControllerExistenciaTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private IndependencyController controller;




    @Test
    void paisExistente_ShouldReturnTrueWhenExist(){

        Long idCountry = 1l;

        when(countryRepository.existsById(idCountry)).thenReturn(true);
    
        ResponseEntity<Boolean> respuesta = controller.paisExistente(idCountry);

        Assertions.assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(respuesta.getBody()).isTrue();


    }


    @Test 
    void paisExistente_ShoudReturnFalseWhenNotExis(){

        Long idCountry = 99l;

        when(countryRepository.existsById(idCountry)).thenReturn(false);

        ResponseEntity<Boolean> respuesta2 = controller.paisExistente(idCountry);

          Assertions.assertThat(respuesta2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(respuesta2.getBody()).isFalse();
    }

}
