package com.mars.assignment.usermanagement.mapper;

import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.entity.Address;
import com.mars.assignment.usermanagement.entity.Person;
import org.hibernate.annotations.Target;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public abstract Person personInfoToPerson(PersonInfo personInfo, @MappingTarget Person person);

    public abstract Address addressInfoToAddress(AddressInfo addressInfo, @MappingTarget Address address);

    @AfterMapping
    protected void addressInfoListToAddressList(@MappingTarget Person person,
                                                                          PersonInfo personInfo) {

        Map<Integer, Address> addressDBMap = getExistingAddress(person.getAddressList());

        if (CollectionUtils.isEmpty(personInfo.getAddressInfos())) {
            person.getAddressList().clear();
            return;
        }

        for (AddressInfo addressInfo : personInfo.getAddressInfos()) {
            Address address;
            if (addressDBMap.containsKey(addressInfo.getId())) {
                address = addressDBMap.get(addressInfo.getId());
            } else {
                address = Address.builder().build();
            }
            address.setPerson(person);
            person.getAddressList().add(addressInfoToAddress(addressInfo, address));
        }
    }

    protected Map<Integer,Address> getExistingAddress(List<Address> addresses){
        Map<Integer,Address> addressDBMap = new HashMap<>();
        for(Address address: addresses){
            addressDBMap.put(address.getId(),address);
        }
        return  addressDBMap;
    }

    @Mapping(target = "addressInfos", source = "addressList")
    public abstract PersonInfo personToPersonInfo(Person person);

    public abstract List<PersonInfo> personsToPersonInfoList(List<Person> persons);

    public abstract AddressInfo addressToAddressInfo(Address address);



}
