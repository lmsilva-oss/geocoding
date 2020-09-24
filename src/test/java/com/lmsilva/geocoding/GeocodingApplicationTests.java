package com.lmsilva.geocoding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GeocodingApplicationTests {
	@MockBean
	private AddressRepository addressRepository;

	@Autowired
	private MockMvc mockMvc;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Address getAddress() {
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
	void testPostAddress() throws Exception {
		Address address = getAddress();

		String expectedResponse = address.toString();

		given(addressRepository.createAddress(any(Address.class))).willReturn(address);

		mockMvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType("application/json")
				.content(expectedResponse))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedResponse));
	}

	@Test
	void testGetAddress() throws Exception {
		Address address = getAddress();
		String expectedResponse = address.toString();

		mockMvc.perform(MockMvcRequestBuilders.get("/address")
				.contentType("application/json")
				.content(expectedResponse))
			.andExpect(status().isNotFound());

		given(addressRepository.readAddress(any(Address.class))).willReturn(address);

		// GET with body is really weird, but the spec says a bunch of parameters are required for all calls
		// in this case, passing a body is cleaner then 1000 query params
		mockMvc.perform(MockMvcRequestBuilders.get("/address")
				.contentType("application/json")
				.content(expectedResponse))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedResponse));
	}

}
