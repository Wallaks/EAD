package com.ead.authuser.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDto {

	// Diferentes visoes de usuarios
	public interface UserView {

		// Cadastro do usuario
		public static interface RegistrationPost {
		}

		// Atualizar dados basicos do usuario
		public static interface userPut {
		}

		// Atualizar senha
		public static interface PasswordPut {
		}

		// Atualizar iamgem
		public static interface imagePut {
		}

	}

	// Dados que vamos receber do cliente para cadastrar no DB
	private UUID userId;

	@JsonView(UserView.RegistrationPost.class)
	private String username;

	@JsonView(UserView.RegistrationPost.class)
	private String email;

	@JsonView({ UserView.RegistrationPost.class, UserView.PasswordPut.class })
	private String password;

	@JsonView({ UserView.PasswordPut.class })
	private String oldPassword;

	@JsonView({ UserView.RegistrationPost.class, UserView.userPut.class })
	private String fullName;

	@JsonView({ UserView.RegistrationPost.class, UserView.userPut.class })
	private String phoneNumber;

	@JsonView({ UserView.RegistrationPost.class, UserView.userPut.class })
	private String cpf;

	@JsonView({ UserView.imagePut.class })
	private String imageUrl;

}
