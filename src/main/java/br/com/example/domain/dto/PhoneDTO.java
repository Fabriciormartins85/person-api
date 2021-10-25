package br.com.example.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.example.domain.model.enumeration.PhoneType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

	private Long id;

	@NotNull(message = "The phone type must not be empty")
	private PhoneType type;

	@NotEmpty(message = "The phone number must not be empty")
	private String number;

}
