package com.davi.agrix.service.interfaces;

import com.davi.agrix.controller.dto.CreatedPerson;
import com.davi.agrix.entity.Person;
import com.davi.agrix.service.exception.PersonNotFoundException;

/**
 * The interface Person service interface.
 */
public interface PersonServiceInterface {

  /**
   * Gets person by id.
   *
   * @param id the id
   * @return the person by id
   * @throws PersonNotFoundException the person not found exception
   */
  public Person getPersonById(Long id) throws PersonNotFoundException;

  /**
   * Gets person by username.
   *
   * @param username the username
   * @return the person by username
   * @throws PersonNotFoundException the person not found exception
   */
  public Person getPersonByUsername(String username) throws PersonNotFoundException;

  /**
   * Create person.
   *
   * @param person the person
   * @return the person
   */
  public CreatedPerson create(Person person);
}
