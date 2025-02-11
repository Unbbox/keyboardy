package com.unbox.Keyboardy.service;

import org.springframework.stereotype.Service;

import com.unbox.Keyboardy.dto.AddressDto;
import com.unbox.Keyboardy.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Long createAddress(AddressDto memberAddressDto) {
        return addressRepository.save(dtoToEntity(memberAddressDto)).getIdx();
    }
    
}
