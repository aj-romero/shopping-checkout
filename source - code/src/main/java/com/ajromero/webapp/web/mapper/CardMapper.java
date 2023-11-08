package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.CardPayment;
import com.ajromero.webapp.web.dto.CCheckoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(target = "securityCode", ignore = true)
    CCheckoutDto toDto(CardPayment card);

    @Mapping(target = "customer", ignore = true)
    CardPayment toEntity(CCheckoutDto dto);
}
