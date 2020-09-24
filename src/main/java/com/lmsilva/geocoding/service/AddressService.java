package com.lmsilva.geocoding.service;

import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.exception.MissingRequiredParameterException;
import com.lmsilva.geocoding.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Value("${geocode.api.key}")
    private String apiKey;

    AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address create(Address address) throws MissingRequiredParameterException {
        Address.validateRequiredFields(address);
        if (shouldFetchAdditionalInfoFromGoogleAPI(address)) {
            String properlyFormattedAddress = Address.toHumanAddress(address);
            GeocodeAPIService.LatitudeLongitude latitudeLongitude =
                    GeocodeAPIService.fetchLatitudeLongitude(properlyFormattedAddress, apiKey);

            if (latitudeLongitude != null) {
                address.setLongitude(latitudeLongitude.getLongitude());
                address.setLatitude(latitudeLongitude.getLatitude());
            }
        }
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

    private boolean shouldFetchAdditionalInfoFromGoogleAPI(Address address) {
        return address.getLatitude() == null || address.getLongitude() == null;
    }
}
