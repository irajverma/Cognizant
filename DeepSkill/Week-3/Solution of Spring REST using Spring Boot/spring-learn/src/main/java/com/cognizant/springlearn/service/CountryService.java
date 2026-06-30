package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * Get a specific country by code (case insensitive).
     * Loads country list from country.xml and iterates/streams to find the match.
     *
     * @param code the country code to search for
     * @return the matching Country
     * @throws CountryNotFoundException if no country matches the code
     */
    @SuppressWarnings("unchecked")
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = (List<Country>) context.getBean("countryList");

        // Using lambda/stream to find country (case insensitive)
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (country == null) {
            LOGGER.error("Country not found for code: {}", code);
            throw new CountryNotFoundException("Country not found for code: " + code);
        }

        LOGGER.debug("Found country: {}", country);
        LOGGER.info("END");
        return country;
    }
}
