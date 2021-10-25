package br.com.example.api.exception.handle;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

	private Integer cod;
	private String message;
	private Instant date;
}
