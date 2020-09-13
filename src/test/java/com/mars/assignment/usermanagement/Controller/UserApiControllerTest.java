package com.mars.assignment.usermanagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.assignment.usermanagement.Model.AddressInfo;
import com.mars.assignment.usermanagement.Model.PersonInfo;
import com.mars.assignment.usermanagement.UserManagementTestWebConfig;
import com.mars.assignment.usermanagement.entity.Person;
import com.mars.assignment.usermanagement.repository.PersonRepository;
import org.aspectj.apache.bcel.classfile.Code;
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
import static org.mockito.Mockito.doNothing;
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
@Import(SecurityConfiguration.class)
public class UserApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PersonRepository personRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeAll
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void whenInvokedPostAddPerson_GivenPersonInfo_ThenReturnHttpStatusCreated()
            throws Exception {

        AddressInfo addressInfo = AddressInfo.builder().city("BBSR").postalCode("700000").state("OD").street("X-Street").build();
        PersonInfo personInfo = PersonInfo.builder().firstName("Biswa").lastName("patro").build();

        when(personRepositoryMock.save(any())).thenReturn(Person.builder().firstName("Biswa").lastName("Patro").build());

        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInfo)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void whenInvokedPutUpdatePerson_GivenPersonInfo_ThenReturnHttpStatusCreated()
            throws Exception {

        AddressInfo addressInfo = AddressInfo.builder().city("BBSR").postalCode("700000").state("OD").street("X-Street").build();
        PersonInfo personInfo = PersonInfo.builder().firstName("BISWA-UPDATED").lastName("patro").build();

        Person person = Person.builder().id(1).firstName("Biswa").lastName("Patro").build();

        when(personRepositoryMock.save(any())).thenReturn(Person.builder().firstName("BISWA-UPDATED").lastName("Patro").build());
        when(personRepositoryMock.findById(any())).thenReturn(Optional.of(person));


        mockMvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInfo)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("BISWA-UPDATED")));

    }

    @Test
    public void whenInvokedDeleteDeletePerson_GivenPersonId_ThenReturnHttpStatusNoContent()
            throws Exception {

        Person person = Person.builder().id(1).firstName("Biswa").lastName("Patro").build();

        when(personRepositoryMock.findById(any())).thenReturn(Optional.of(person));

        mockMvc.perform(delete("/person/1").contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void whenInvokedGetGetAllPersons_ThenReturnPersonInfoList()
            throws Exception {

        Person person = Person.builder().id(1).firstName("Biswa").lastName("Patro").build();
        Person personTwo = Person.builder().id(2).firstName("Rahul").lastName("Dash").build();
        Person personThree = Person.builder().id(3).firstName("Mars").lastName("Telecom").build();

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(personTwo);
        personList.add(personThree);

        when(personRepositoryMock.findAll()).thenReturn(personList);


        mockMvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()",is(3)));

    }

    @Test
    public void whenInvokedGetGetAllPersonsCount_ThenReturnPersonInfoList()
            throws Exception {

        Person person = Person.builder().id(1).firstName("Biswa").lastName("Patro").build();
        Person personTwo = Person.builder().id(2).firstName("Rahul").lastName("Dash").build();
        Person personThree = Person.builder().id(3).firstName("Mars").lastName("Telecom").build();

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(personTwo);
        personList.add(personThree);

        when(personRepositoryMock.findAll()).thenReturn(personList);


        mockMvc.perform(get("/person/count").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", is(3)));

    }
}