package com.lmsilva.geocoding.repository;

import com.lmsilva.geocoding.data.Address;

public interface AddressRepository {

    Address createAddress(Address address);

    Address readAddress(Address address);

    Address updateAddress(Address address);

    void deleteAddress(Address address);
}
