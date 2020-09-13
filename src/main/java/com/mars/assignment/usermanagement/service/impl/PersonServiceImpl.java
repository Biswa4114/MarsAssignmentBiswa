package com.mars.assignment.usermanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.entity.Person;
import com.mars.assignment.usermanagement.exception.UserManagementException;
import com.mars.assignment.usermanagement.mapper.PersonMapper;
import com.mars.assignment.usermanagement.repository.PersonRepository;
import com.mars.assignment.usermanagement.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addPerson(PersonInfo personInfo) {

        if(Objects.isNull(personInfo)){
            throw new UserManagementException("Input Request cannot be Null","Invalid Input request", HttpStatus.BAD_REQUEST);
        }

       Person person =  personMapper.personInfoToPerson(personInfo, new Person());
       personRepository.save(person);


    }

    @Override
    public PersonInfo updatePerson(Integer personid, PersonInfo personInfo) {
        if(Objects.isNull(personid) || Objects.isNull(personInfo)){
            throw new UserManagementException("Input Request cannot be Null","Invalid Input request", HttpStatus.BAD_REQUEST);
        }

        Optional<Person> personOptional = personRepository.findById(personid);

        if(!personOptional.isPresent()){
            throw new UserManagementException("No Person Found","No Person Found", HttpStatus.NOT_FOUND);
        }

        Person person = personRepository.save(personMapper.personInfoToPerson(personInfo, personOptional.get()));
        return personMapper.personToPersonInfo(person);

    }

    @Override
    public void deletePerson(Integer personId) {

        if(Objects.isNull(personId)){
            throw new UserManagementException("Person Id cannot be Null","Invalid Person Id", HttpStatus.BAD_REQUEST);
        }

        Optional<Person> personOptional = personRepository.findById(personId);

        if(!personOptional.isPresent()){
            throw new UserManagementException("No Person Found","No Person Found", HttpStatus.NOT_FOUND);
        }

        personRepository.deleteById(personId);
    }

    @Override
    public List<PersonInfo> getAllPersons() {
        List<Person> persons = personRepository.findAll();

        if(CollectionUtils.isEmpty(persons)){
            throw new UserManagementException("No Persons Found","No Persons Found", HttpStatus.NOT_FOUND);
        }

        return personMapper.personsToPersonInfoList(persons);
    }

    @Override
    public Integer getCountAllPersons() {
        return personRepository.findAll().size();
    }


}
