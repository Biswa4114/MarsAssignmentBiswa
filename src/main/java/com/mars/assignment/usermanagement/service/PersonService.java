package com.mars.assignment.usermanagement.service;

import com.mars.assignment.usermanagement.Model.PersonInfo;

import java.util.List;

public interface PersonService {

    void addPerson(PersonInfo personInfo);

    PersonInfo updatePerson(Integer personid, PersonInfo personInfo);

    void deletePerson(Integer personId);

    List<PersonInfo> getAllPersons();

    Integer getCountAllPersons();
}
