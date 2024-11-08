package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.CreatedPerson;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.repository.PersonRepository;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import com.betrybe.agrix.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Person service.
 */
@Service
public class PersonService implements PersonServiceInterface {

  private final PersonRepository personRepository;

  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   */
  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Person getPersonById(Long id) throws PersonNotFoundException {
    return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
  }

  @Override
  public Person getPersonByUsername(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username).orElseThrow(PersonNotFoundException::new);
  }

  @Override
  public CreatedPerson create(Person person) {
    Person newPerson = personRepository.save(person);
    return new CreatedPerson(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getRole()
    );
  }
}
