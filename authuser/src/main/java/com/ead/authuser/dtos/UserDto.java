package com.ead.authuser.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDto {

	// Dados que vamos receber do cliente para cadastrar no DB
	private UUID userId;
	private String username;
	private String email;
	private String password;
	private String oldPassword;
	private String fullName;
	private String phoneNumber;
	private String cpf;
	private String imageUrl;

}
