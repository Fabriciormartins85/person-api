package br.com.example.domain.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.example.domain.dto.PersonDTO;
import br.com.example.domain.dto.PhoneDTO;
import br.com.example.domain.model.enumeration.PhoneType;

@RunWith(SpringRunner.class)
////@WebMvcTest(controllers = PersonResource.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PersonResourceTest {

	private static final String API_V1_PERSON = "/v1/person";
	private static final String MESSAGE_PERSON_NOT_FOUND = "Person Not Found";

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;

	private PersonDTO personDTO;
	
	@BeforeEach
	void setUp() {
//		this.personResource = new PersonResource(personService);
//
//		mockMvc = MockMvcBuilders.standaloneSetup(personResource)
//				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//				.setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
		this.mapper = new ObjectMapper();
		this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		this.mapper.registerModules(new JavaTimeModule());
		this.personDTO =	PersonDTO.builder().firstName("TESTE").lastName("TESTE").cpf("31579259030")
		.birthDate(LocalDate.now()).build();
		this.personDTO.getPhones().add(PhoneDTO.builder().number("1234").type(PhoneType.MOBILE).build());
	}

	@Test
	public void testFindById() throws Exception {

		mockMvc.perform(get(String.format("%s/%d", API_V1_PERSON, 2l)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is(MESSAGE_PERSON_NOT_FOUND)));
	}

	@Test
	void testWhenPOSTIsCalledThenAPersonShouldBeCreated() throws Exception {
		
		var jsonPerson = this.mapper.writeValueAsString(this.personDTO);
		mockMvc.perform(post(API_V1_PERSON).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPerson))
				.andExpect(status().isCreated());
	}
    @Test
    void testWhenGETWithValidIsCalledThenAPersonShouldBeReturned() throws Exception {
        var expectedValidId = 1L;
        mockMvc.perform(get(API_V1_PERSON + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(this.personDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(this.personDTO.getLastName())));
    }
}
