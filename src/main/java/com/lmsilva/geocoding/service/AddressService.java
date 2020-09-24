package com.lmsilva.geocoding.service;

import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address create(Address address) {
        if (Address.validateRequiredFields(address)) {
            return repository.createAddress(address);
        } else {
            return null;
        }
    }

    public Address read(Address address) {
        if (Address.validateRequiredFields(address)) {
            return repository.readAddress(address);
        } else {
            return null;
        }
    }

    public Address update(Address address) {
        if (Address.validateRequiredFields(address)) {
            return repository.updateAddress(address);
        } else {
            return null;
        }
    }

    public Address delete(Address address) {
        if (Address.validateRequiredFields(address)) {
            repository.deleteAddress(address);
            return address;
        } else {
            return  null;
        }
    }
}
