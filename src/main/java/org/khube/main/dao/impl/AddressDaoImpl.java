package org.khube.main.dao.impl;

import org.khube.main.dao.AddressDao;
import org.khube.main.entity.secondary.Address;
import org.khube.main.repository.secondary.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressDaoImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
