package com.ajromero.webapp.web.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


class AddressDtoTest {
    AddressDto addressDto = new AddressDto();

    @Test
    void whenCreateNewAddressDto_thenAssert() {
        Long id = 1L;
        String addressName = "My Address Name";
        String state = "California";
        String city = "Panorama City";
        String street = "Bellavista st";
        String refPhone = "+503 4434 3321";
        String refName = "Henry Romero";
        String houseNumber = "345LD";
        addressDto.setAddressName(addressName);
        addressDto.setId(id);
        addressDto.setState(state);
        addressDto.setCity(city);
        addressDto.setStreet(street);
        addressDto.setReferencePhone(refPhone);
        addressDto.setReferenceName(refName);
        addressDto.setHouseNumber(houseNumber);

        assertAll(
                ()->assertThat(addressDto.getAddressName(),is(equalTo(addressName))),
                ()->assertThat(addressDto.getCity(),is(equalTo(city))),
                ()->assertThat(addressDto.getState(),is(equalTo(state))),
                ()->assertThat(addressDto.getStreet(),is(equalTo(street))),
                ()->assertThat(addressDto.getReferenceName(),is(equalTo(refName))),
                ()->assertThat(addressDto.getReferencePhone(),is(equalTo(refPhone))),
                ()->assertThat(addressDto.getHouseNumber(),is(equalTo(houseNumber))),
                ()->assertThat(addressDto.getId(),is(equalTo(id)))
        );


    }

}

