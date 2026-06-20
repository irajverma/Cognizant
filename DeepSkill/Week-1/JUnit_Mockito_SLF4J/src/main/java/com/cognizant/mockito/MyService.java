package com.cognizant.mockito;

/**
 * Service that depends on ExternalApi for Mockito exercises.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataWithParam(String param) {
        return externalApi.getDataWithParam(param);
    }

    public void process(String data) {
        externalApi.processData(data);
    }
}
