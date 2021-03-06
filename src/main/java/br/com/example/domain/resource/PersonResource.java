package br.com.example.domain.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.domain.dto.PersonDTO;
import br.com.example.domain.service.PersonService;

@RestController
@RequestMapping("v1/person")
public class PersonResource {

	private PersonService service;
	

	public PersonResource(PersonService service) {
		this.service = service;
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> personById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> allPersons() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> save(@RequestBody @Valid PersonDTO person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> update(@RequestBody @Valid PersonDTO person) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(person));
	}

	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> delete(@RequestBody @Valid PersonDTO person) {
		service.delete(person);
		return ResponseEntity.noContent().build();
	}

}
