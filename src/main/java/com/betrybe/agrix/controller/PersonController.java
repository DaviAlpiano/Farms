package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CreatedPerson;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import com.betrybe.agrix.service.interfaces.PersonServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private PersonServiceInterface personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  public PersonController(PersonServiceInterface personService) {
    this.personService = personService;
  }

  /**
   * Created person response entity.
   *
   * @param person the person
   * @return the response entity
   * @throws PersonNotFoundException the person not found exception
   */
  @PostMapping
  public ResponseEntity<CreatedPerson> createdPerson(@RequestBody Person person)
      throws PersonNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
  }
}
