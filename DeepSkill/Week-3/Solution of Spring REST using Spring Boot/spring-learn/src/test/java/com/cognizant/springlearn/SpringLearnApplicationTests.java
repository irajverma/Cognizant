package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    /**
     * Test that the application context loads and CountryController is initialized.
     */
    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    /**
     * Test GET /countries/country - verify India country details are returned.
     * Checks that response contains code="IN" and name="India".
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/country"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Test GET /countries/{code} with an invalid country code.
     * Expects a 404 Not Found response with reason "Country not found".
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Country not found"));
    }

    /**
     * Test GET /countries - verify all countries are returned.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].code").exists());
    }
}
