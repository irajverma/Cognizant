package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = new SpringLearnApplication();
        app.displayDate();
        app.displayCountry();
        app.displayCountries();

        LOGGER.info("END");
    }

    /**
     * Hands-on 2 & 3: Load SimpleDateFormat from Spring XML and parse a date string.
     * Demonstrates Spring Core XML bean loading with logging.
     */
    public void displayDate() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", date.toString());
        } catch (Exception e) {
            LOGGER.error("Error parsing date: {}", e.getMessage());
        }
        LOGGER.info("END");
    }

    /**
     * Hands-on 4 & 5: Load Country bean from Spring XML configuration.
     * Demonstrates singleton scope (default) - constructor called only once for multiple getBean() calls.
     */
    public void displayCountry() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // First getBean() call
        Country country = context.getBean("in", Country.class);
        LOGGER.debug("Country : {}", country.toString());

        // Second getBean() call - demonstrates Singleton scope (same instance returned)
        Country anotherCountry = context.getBean("in", Country.class);
        LOGGER.debug("Same instance (Singleton): {}", (country == anotherCountry));

        LOGGER.info("END");
    }

    /**
     * Hands-on 6: Load list of countries from Spring XML configuration.
     * Demonstrates loading ArrayList bean with country references.
     */
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        List<Country> countries = (List<Country>) context.getBean("countryList");
        for (Country country : countries) {
            LOGGER.debug("Country: {}", country.toString());
        }

        LOGGER.info("END");
    }
}
