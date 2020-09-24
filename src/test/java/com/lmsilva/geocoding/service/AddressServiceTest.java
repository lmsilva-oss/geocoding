package com.lmsilva.geocoding.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.repository.AddressRepository;
import com.lmsilva.geocoding.repository.impl.InMemoryAddressRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {

    AddressService getInMemoryAddressService() {
        AddressRepository repository = new InMemoryAddressRepository();
        return new AddressService(repository);
    }

    Address getTestAddress() {
        Address address = new Address();
        address.setId("testId");
        address.setStreetName("testStreetName");
        address.setNumber("testNumber");
        address.setNeighborhood("testNeighborhood");
        address.setCity("testCity");
        address.setState("testState");
        address.setCountry("testCountry");
        address.setZipcode("testZipcode");

        return address;
    }

    @Test
    void testUpdate() throws Exception {
        AddressService service = getInMemoryAddressService();

        Address address = getTestAddress();

        service.create(address);

        Address updated = new ObjectMapper().readValue(address.toString(), Address.class); // ensure we're doing a deep copy

        updated.setStreetName("updatedStreetName");
        Address result = service.update(updated);

        assertNotEquals(address.toString(), result.toString());
        assertEquals(updated.toString(), result.toString());
    }

    @Test
    void testRead() throws Exception {
        AddressService service = getInMemoryAddressService();
        Address address = getTestAddress();

        service.create(address);

        Address read = service.read(address);

        assertEquals(address.toString(), read.toString());
    }
}