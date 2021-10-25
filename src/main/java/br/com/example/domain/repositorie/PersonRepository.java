package br.com.example.domain.repositorie;

import org.springframework.data.repository.CrudRepository;

import br.com.example.domain.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
