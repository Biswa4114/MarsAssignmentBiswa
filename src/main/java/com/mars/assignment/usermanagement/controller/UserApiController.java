package com.mars.assignment.usermanagement.controller;


import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "User")
public class UserApiController implements UserApi{

    @Autowired
    private PersonService personService;

    @Override
    public ResponseEntity<Void> addPerson(PersonInfo personInfo) {
        personService.addPerson(personInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PersonInfo> updatePerson(Integer personId, PersonInfo personInfo) {
        return new ResponseEntity<>(personService.updatePerson(personId,personInfo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePerson(Integer personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<PersonInfo>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getCountAllPersons() {
        return new ResponseEntity<>(personService.getCountAllPersons(), HttpStatus.OK);
    }
}
