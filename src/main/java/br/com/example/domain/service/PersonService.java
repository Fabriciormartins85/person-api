package br.com.example.domain.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.api.exception.NotFoundException;
import br.com.example.domain.dto.PersonDTO;
import br.com.example.domain.model.Person;
import br.com.example.domain.repositorie.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repo;

	private ModelMapper mapper;
	
	@Autowired
	public PersonService(PersonRepository repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public PersonDTO findById(final Long id) throws NotFoundException {
		Optional<Person> optPerson = repo.findById(id);
		return mapper.map(optPerson.orElseThrow(() -> new NotFoundException("Person Not Found")), PersonDTO.class);
	}
	public PersonDTO save(PersonDTO personDTO) throws NotFoundException {
		return mapper.map(repo.save(mapper.map(personDTO, Person.class)), PersonDTO.class);
	}

}
