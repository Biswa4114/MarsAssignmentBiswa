package com.mars.assignment.usermanagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.UserManagementTestWebConfig;
import com.mars.assignment.usermanagement.entity.Address;
import com.mars.assignment.usermanagement.entity.Person;
import com.mars.assignment.usermanagement.repository.AddressRepository;
import com.mars.assignment.usermanagement.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { UserManagementTestWebConfig.class })
@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = { "spring.profiles.active=test" })
public class AddressApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AddressRepository addressRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeAll
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    public void whenInvokedPutUpdateAddress_GivenAddressInfo_ThenReturnHttpStatusSuccess()
            throws Exception {

        AddressInfo addressInfo = AddressInfo.builder().city("BBSR-Updated").postalCode("700000").state("OD").street("X-Street").build();

        Address address = Address.builder().id(1).city("BBSR").postalCode("700000").state("OD").street("X-Street").build();


        when(addressRepositoryMock.save(any())).thenReturn(Address.builder().id(1).city("BBSR-Updated").postalCode("700000").state("OD").street("X-Street").build());
        when(addressRepositoryMock.findById(any())).thenReturn(Optional.of(address));


        mockMvc.perform(put("/address/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressInfo)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.city", is("BBSR-Updated")));

    }

    @Test
    public void whenInvokedDeleteDeleteAddress_GivenAddressId_ThenReturnHttpStatusNoContent()
            throws Exception {

        Address address = Address.builder().id(1).city("BBSR").postalCode("700000").state("OD").street("X-Street").build();

        when(addressRepositoryMock.findById(any())).thenReturn(Optional.of(address));

        mockMvc.perform(delete("/address/1").contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}