package com.mars.assignment.usermanagement.service;

import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;

public interface AddressService {

    AddressInfo updateAddress(Integer addressId, AddressInfo addressInfo);

    void deleteAddress(Integer personId);
}
