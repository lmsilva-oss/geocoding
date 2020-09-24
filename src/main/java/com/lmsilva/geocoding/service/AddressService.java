package com.lmsilva.geocoding.service;

import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.exception.MissingRequiredParameterException;
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

    public Address create(Address address) throws MissingRequiredParameterException {
        Address.validateRequiredFields(address);
        return repository.createAddress(address);
    }

    public Address read(Address address) throws MissingRequiredParameterException {
        Address.validateRequiredFields(address);
        return repository.readAddress(address);
    }

    public Address update(Address address) throws MissingRequiredParameterException {
        Address.validateRequiredFields(address);
        return repository.updateAddress(address);
    }

    public void delete(Address address) throws MissingRequiredParameterException {
        Address.validateRequiredFields(address);
        repository.deleteAddress(address);
    }
}
