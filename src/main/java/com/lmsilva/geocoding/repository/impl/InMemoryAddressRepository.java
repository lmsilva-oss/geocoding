package com.lmsilva.geocoding.repository.impl;

import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.repository.AddressRepository;

import java.util.HashMap;
import java.util.Map;

// no @Component since we want to use this for testing only
// there should be a way to filter this using @Qualifiers
public class InMemoryAddressRepository implements AddressRepository {
    Map<String, Address> repository = new HashMap<>();

    @Override
    public Address createAddress(Address address) {
        repository.put(address.getId(), address);
        return address;
    }

    @Override
    public Address readAddress(Address address) {
        return repository.get(address.getId());
    }

    @Override
    public Address updateAddress(Address address) {
        repository.put(address.getId(), address);
        return address;
    }

    @Override
    public void deleteAddress(Address address) {
        repository.remove(address.getId());
    }
}
