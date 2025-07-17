package org.khube.main.service.impl;

import org.khube.main.dao.AddressDao;
import org.khube.main.entity.secondary.Address;
import org.khube.main.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Address createAddress(Address address) {
        if (address == null){
            throw new IllegalArgumentException("Address must not be null");
        }
        return addressDao.createAddress(address);
    }
}
