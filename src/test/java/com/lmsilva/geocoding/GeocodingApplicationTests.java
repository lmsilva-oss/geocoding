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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	@Test
	void testCreateAddress() throws Exception {
		Address address = new Address();
		address.setId("testId");
		address.setStreetName("testStreetName");
		address.setNumber("testNumber");
		address.setNeighborhood("testNeighborhood");
		address.setCity("testCity");
		address.setState("testState");
		address.setCountry("testCountry");
		address.setZipcode("testZipcode");

		String expectedResponse = MAPPER.writeValueAsString(address);

		given(addressRepository.createAddress(any(Address.class))).willReturn(address);

		mockMvc.perform(post("/address")
				.contentType("application/json")
				.content(expectedResponse))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedResponse));
	}

}
