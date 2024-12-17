package com.davi.agrix.controller.dto;

import com.davi.agrix.security.Role;

/**
 * The type Created person.
 */
public record CreatedPerson(Long id, String username, Role role) {

}
