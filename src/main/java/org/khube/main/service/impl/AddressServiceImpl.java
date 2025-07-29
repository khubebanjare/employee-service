package org.khube.main.service.impl;

import org.khube.main.entity.Address;
import org.khube.main.repository.secondary.AddressRepository;
import org.khube.main.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        if (address == null){
            throw new IllegalArgumentException("Address must not be null");
        }
        return addressRepository.save(address);
    }
}
