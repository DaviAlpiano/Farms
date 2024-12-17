package com.davi.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.davi.agrix.controller.dto.CreatedPerson;
import com.davi.agrix.entity.Person;
import com.davi.agrix.repository.PersonRepository;
import com.davi.agrix.security.Role;
import com.davi.agrix.service.PersonService;
import com.davi.agrix.service.exception.PersonNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste da PersonService")
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  @DisplayName("Testando getPersonById")
  public void testGetPersonId() throws PersonNotFoundException {
    Person person = new Person();
    person.setId(1L);
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

    Person serviceP = personService.getPersonById(1L);
    assertEquals(1L, serviceP.getId());
  }

  @Test
  @DisplayName("Testando getPersonByUsername")
  public void testGetPersonByUsername() throws PersonNotFoundException {
    Person person = new Person();
    person.setUsername("Paulo");
    Mockito.when(personRepository.findByUsername("Paulo")).thenReturn(Optional.of(person));

    Person serviceP = personService.getPersonByUsername("Paulo");
    assertEquals("Paulo", serviceP.getUsername());
  }

  @Test
  @DisplayName("Testando create")
  public void testCreate() {
    Person person = new Person();
    Role role = Role.ADMIN;
    person.setId(1L);
    person.setUsername("Paulo");
    person.setPassword("dev");
    person.setRole(role);
    Mockito.when(personRepository.save(person)).thenReturn(person);

    CreatedPerson serviceP = personService.create(person);
    assertEquals(person.getId(), serviceP.id());
  }
}
