package com.lmsilva.geocoding.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsilva.geocoding.exception.MissingRequiredParameterException;

public class Address {
    private String id;
    private String streetName;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String latitude;
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static String getHumanAddress(Address address) {
        return String.format(
                "%s %s, %s, %s, %s", address.getNumber(), address.getStreetName(), address.getCity(), address.getState(), address.getCountry()
        );
    }

    public static void validateRequiredFields(Address address) throws MissingRequiredParameterException {
        if (address.getId() == null) {
            throwException("id");
        }

        if (address.getStreetName() == null) {
            throwException("Street Name");
        }

        if (address.getNumber() == null) {
            throwException("Number");
        }

        if (address.getNeighborhood() == null) {
            throwException("Neighborhood");
        }

        if (address.getCity() == null) {
            throwException("City");
        }

        if (address.getState() == null) {
            throwException("State");
        }

        if (address.getCountry() == null) {
            throwException("Country");
        }

        if (address.getZipcode() == null) {
            throwException("Zipcode");
        }
    }

    private static void throwException(String missingFieldName) throws MissingRequiredParameterException {
        String BASE_MESSAGE = "Missing address field: %s";
        throw new MissingRequiredParameterException(String.format(BASE_MESSAGE, missingFieldName));
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
