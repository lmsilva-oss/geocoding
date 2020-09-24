package com.lmsilva.geocoding.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsilva.geocoding.exception.MissingRequiredParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    public void testSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String expectedJson = "{\"id\":\"testId\",\"streetName\":\"testStreet\",\"number\":\"12A\",\"complement\":\"testComplement\",\"neighborhood\":\"testNeighborhood\",\"city\":\"testCity\",\"state\":\"testState\",\"country\":\"testCountry\",\"zipcode\":\"testZipcode\",\"latitude\":\"testLatitude\",\"longitude\":\"testLongitude\"}";

        Address address = new Address();
        address.setId("testId");
        address.setStreetName("testStreet");
        address.setNumber("12A");
        address.setComplement("testComplement");
        address.setNeighborhood("testNeighborhood");
        address.setCity("testCity");
        address.setState("testState");
        address.setCountry("testCountry");
        address.setZipcode("testZipcode");
        address.setLatitude("testLatitude");
        address.setLongitude("testLongitude");

        assertEquals(expectedJson, mapper.writeValueAsString(address));
        Address.validateRequiredFields(address);
    }

    @Test
    public void testRequiredFields() {
        try {
            Address.validateRequiredFields(new Address());
            fail("Should've thrown MissingRequiredParameterException!");
        } catch (MissingRequiredParameterException e) {
            assertNotNull(e.getMessage());
        }
    }
}