package com.unbox.Keyboardy.service;

import com.unbox.Keyboardy.dto.AddressDto;
import com.unbox.Keyboardy.entity.Address;

public interface AddressService {

    Long createAddress(AddressDto memberAddressDto);

    public default AddressDto entityToDto(Address address) {
        return AddressDto.builder()
        .postCode(address.getPostCode())
        .roadAddress(address.getRoadAddress())
        .detailAddress(address.getDetailAddress())
        .extraAddress(address.getExtraAddress())
        .build();
    }

    public default Address dtoToEntity(AddressDto dto) {
        return Address.builder()
        .postCode(dto.getPostCode())
        .roadAddress(dto.getRoadAddress())
        .detailAddress(dto.getDetailAddress())
        .extraAddress(dto.getExtraAddress())
        .build();
    }
}
