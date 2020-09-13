package com.mars.assignment.usermanagement.controller;

import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "Address")
@Validated
public interface AddressApi {

    @ApiOperation(value = "Edit a Persons Address", nickname = "updateAddress", tags = "Address",response = PersonInfo.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @PutMapping(value = "/address/{addressid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressInfo> updateAddress(
            @ApiParam(value = "address id", required = true) @PathVariable("addressid") Integer addressId,
            @RequestBody AddressInfo addressInfo);

    @ApiOperation(value = "Delete a Address", nickname = "deleteAddress", tags = "Address")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully Deleted"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Unable to fulfill your request due to some unexpected error") })
    @DeleteMapping(value = "/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteAddress(
            @ApiParam(value = "person Id", required = true) @PathVariable(value = "addressId") Integer addressId);

}
