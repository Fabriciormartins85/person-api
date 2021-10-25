package br.com.example.domain.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

	private Long id;

	@NotEmpty(message = "First name must not be blank")
	@Size(min = 2, max = 100, message = "First name length is minimum 2 to 100 characters")
	private String firtName;

	@NotEmpty(message = "Last name must not be blank")
	@Size(min = 2, max = 100, message = "Last name length is minimum 2 to 100 characters")
	private String lastName;

	@CPF(message = "CPF must be valid")
	private String cpf;
	
	@NotNull(message = "Birth date not be empty")
	@DateTimeFormat(iso= ISO.DATE, pattern = "yyyy-MM-dd", fallbackPatterns = { "dd-MM-yyyy", "dd/MM/yyyy" })
	@JsonFormat(pattern = "dd/MM/yyyy" )
	private LocalDate birthDate;

	@Valid
	@NotEmpty(message = "The phones must not be blank")
	private Set<PhoneDTO> phones = new HashSet<>();
}
