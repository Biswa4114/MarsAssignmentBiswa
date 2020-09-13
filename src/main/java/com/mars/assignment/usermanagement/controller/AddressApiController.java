package com.mars.assignment.usermanagement.controller;


import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.service.AddressService;
import com.mars.assignment.usermanagement.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Address")
public class AddressApiController implements AddressApi{

    @Autowired
    private AddressService addressService;


    @Override
    public ResponseEntity<AddressInfo> updateAddress(Integer addressId, AddressInfo addressInfo) {
        return new ResponseEntity<>(addressService.updateAddress(addressId,addressInfo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAddress(Integer addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
