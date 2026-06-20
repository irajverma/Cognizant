package com.cognizant.mockito.advanced;

/**
 * Service that depends on Repository for Advanced Mockito Exercise 1 & 5.
 */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        return "Processed " + data;
    }
}
