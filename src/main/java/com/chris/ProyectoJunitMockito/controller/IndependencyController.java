package com.chris.ProyectoJunitMockito.controller;

import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chris.ProyectoJunitMockito.models.Country;
import com.chris.ProyectoJunitMockito.models.CountryResponse;
import com.chris.ProyectoJunitMockito.repositories.CountryRepository;
import com.chris.ProyectoJunitMockito.util.DiferenciaEntreFechas;

@RestController()
public class IndependencyController {

    CountryResponse countryResponse;
    Optional<Country> country;
    CountryRepository countryRepository;
    DiferenciaEntreFechas diferenciaEntreFechas;

    public IndependencyController(CountryRepository countryRepository, DiferenciaEntreFechas diferenciaEntreFechas) {
        this.countryRepository = countryRepository;
        this.diferenciaEntreFechas = diferenciaEntreFechas;
    }

    @GetMapping(path = "/country/{countryId}")
    public ResponseEntity<CountryResponse> getCountryDetails(@PathVariable("countryId") String countryId) {
        country = Optional.of(new Country());
        countryResponse = new CountryResponse();

        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(countryId.toUpperCase()));

        if (country.isPresent()) {
            Period period = diferenciaEntreFechas
                    .calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
            countryResponse.setCountryName(country.get().getCountryName());
            countryResponse.setCapitalName(country.get().getCountryCapital());
            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
            countryResponse.setDayssOfIndependency(period.getDays());
            countryResponse.setMonthsOfIndependency(period.getMonths());
            countryResponse.setYearsOfIndependency(period.getYears());
        }
        return ResponseEntity.ok(countryResponse);
    }


//cometario para git
    @GetMapping("/todosPaises")

        Iterable<Country> iterable = countryRepository.findAll();
        List<Country> paises = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(paises, HttpStatus.OK);
    }

    @GetMapping(path = "/country/{countryId}")
    public ResponseEntity<Boolean> paisExistente(@PathVariable("countryId") Long countryid) {

        boolean personaExistente = countryRepository.existsById(countryid);

        if (personaExistente) {

            return ResponseEntity.ok(true);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

    }

}