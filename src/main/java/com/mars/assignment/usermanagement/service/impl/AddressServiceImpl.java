package com.mars.assignment.usermanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.entity.Address;
import com.mars.assignment.usermanagement.exception.UserManagementException;
import com.mars.assignment.usermanagement.mapper.PersonMapper;
import com.mars.assignment.usermanagement.repository.AddressRepository;
import com.mars.assignment.usermanagement.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AddressInfo updateAddress(Integer addressId, AddressInfo addressInfo) {
        if(Objects.isNull(addressId) || Objects.isNull(addressInfo)){
            throw new UserManagementException("Input Request cannot be Null","Invalid Input request", HttpStatus.BAD_REQUEST);
        }

        Optional<Address> addressOptional = addressRepository.findById(addressId);

        if(!addressOptional.isPresent()){
            throw new UserManagementException("No Address Found","No Address Found", HttpStatus.NOT_FOUND);
        }

       Address address =  addressRepository.save(personMapper.addressInfoToAddress(addressInfo,addressOptional.get()));

        return personMapper.addressToAddressInfo(address);

    }

    @Override
    public void deleteAddress(Integer addressId) {
        if(Objects.isNull(addressId)){
            throw new UserManagementException("Address Id cannot be Null","Invalid Address Id", HttpStatus.BAD_REQUEST);
        }

        Optional<Address> addressOptional = addressRepository.findById(addressId);

        if(!addressOptional.isPresent()){
            throw new UserManagementException("No Address Found","No Address Found", HttpStatus.NOT_FOUND);
        }

        addressRepository.deleteById(addressId);
    }
}
