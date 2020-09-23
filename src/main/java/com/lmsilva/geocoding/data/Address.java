package com.lmsilva.geocoding.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty
    private String id;
    @JsonProperty
    private String streetName;
    @JsonProperty
    private String number;
    @JsonProperty
    private String complement;
    @JsonProperty
    private String neighbourhood;
    @JsonProperty
    private String city;
    @JsonProperty
    private String state;
    @JsonProperty
    private String country;
    @JsonProperty
    private String zipcode;
    @JsonProperty
    private String latitude;
    @JsonProperty
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

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
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

    public static boolean validateRequiredFields(Address address) {
        return address.getId() != null
                && address.getStreetName() != null
                && address.getNumber() != null
                && address.getNeighbourhood() != null
                && address.getCity() != null
                && address.getState() != null
                && address.getCountry() != null
                && address.getZipcode() != null;
    }
}
