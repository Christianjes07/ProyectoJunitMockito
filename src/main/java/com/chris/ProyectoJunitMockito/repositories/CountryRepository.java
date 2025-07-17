package com.chris.ProyectoJunitMockito.repositories;

import org.springframework.data.repository.CrudRepository;

import com.chris.ProyectoJunitMockito.models.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

    Country findCountryByIsoCode(String isoCode);

}
