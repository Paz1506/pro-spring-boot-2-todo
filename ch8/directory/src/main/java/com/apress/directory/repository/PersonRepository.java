package com.apress.directory.repository;

import com.apress.directory.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Pavel Zaytsev
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    Person findByEmailIgnoreCase(@Param("email") String email);

}
