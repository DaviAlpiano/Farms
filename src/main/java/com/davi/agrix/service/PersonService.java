package com.davi.agrix.service;

import com.davi.agrix.controller.dto.CreatedPerson;
import com.davi.agrix.entity.Person;
import com.davi.agrix.repository.PersonRepository;
import com.davi.agrix.service.exception.PersonNotFoundException;
import com.davi.agrix.service.interfaces.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Person service.
 */
@Service
public class PersonService implements PersonServiceInterface, UserDetailsService {

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
    String hashedPassword = new BCryptPasswordEncoder()
        .encode(person.getPassword());

    person.setPassword(hashedPassword);

    Person newPerson = personRepository.save(person);

    return new CreatedPerson(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getRole()
    );
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
