package com.lmsilva.geocoding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.exception.MissingRequiredParameterException;
import com.lmsilva.geocoding.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private AddressService addressService;

    @Autowired
    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<String> createAddress(@RequestBody Address address) {
        try {
            Address result = addressService.create(address);
            return ResponseEntity.ok(MAPPER.writeValueAsString(result));
        } catch (MissingRequiredParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/address")
    public ResponseEntity<String> updateAddress(@RequestBody Address address) {
        try {
            Address result = addressService.update(address);
            return ResponseEntity.ok(MAPPER.writeValueAsString(result));
        } catch (MissingRequiredParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/address")
    public ResponseEntity<String> deleteAddress(@RequestBody Address address) {
        try {
            addressService.delete(address);
            return ResponseEntity.ok(String.format("Successfully deleted %s", address.getId()));
        } catch (MissingRequiredParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
