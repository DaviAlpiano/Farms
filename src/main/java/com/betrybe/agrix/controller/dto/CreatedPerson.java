package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.security.Role;

/**
 * The type Created person.
 */
public record CreatedPerson(Long id, String username, Role role) {

}
