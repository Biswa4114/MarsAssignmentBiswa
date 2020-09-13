package com.mars.assignment.usermanagement.controller;

import com.mars.assignment.usermanagement.Model.PersonInfo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "User")
@Validated
public interface UserApi {

    @ApiOperation(value = "Add a Person", nickname = "addPerson", tags = "User")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addPerson(
            @RequestBody PersonInfo personInfo);

    @ApiOperation(value = "Edit a Person", nickname = "updatePerson", tags = "User",response = PersonInfo.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @PutMapping(value = "/person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PersonInfo> updatePerson(
            @ApiParam(value = "person id", required = true) @PathVariable("personid")  Integer personId,
            @RequestBody PersonInfo personInfo);

    @ApiOperation(value = "Delete a Person", nickname = "deletePerson", tags = "User")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully Deleted"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @DeleteMapping(value = "/person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletePerson(
            @ApiParam(value = "person Id", required = true) @PathVariable(value = "personid") Integer personId);

    @ApiOperation(value = "Get persons", nickname = "getAllPersons", tags = "User")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PersonInfo>> getAllPersons();

    @ApiOperation(value = "Get count of all persons", nickname = "getCountAllPersons", tags = "User")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @GetMapping(value = "/person/count", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getCountAllPersons();

}
