package com.davi.agrix.repository;

import com.davi.agrix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Farm repository.
 */
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
