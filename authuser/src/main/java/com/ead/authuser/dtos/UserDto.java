package com.ead.authuser.dtos;

import java.util.UUID;

import com.ead.authuser.validations.UserNameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDto {

	// Diferentes visoes de usuarios
	public interface UserView {

		// Cadastro do usuario
		public static interface RegistrationPost {
		}

		// Atualizar dados basicos do usuario
		public static interface UserPut {
		}

		// Atualizar senha
		public static interface PasswordPut {
		}

		// Atualizar iamgem
		public static interface ImagePut {
		}

	}

	// Dados que vamos receber do cliente para cadastrar no DB
	private UUID userId;
	@NotBlank(groups = UserView.RegistrationPost.class) // Não permite valores nulos ou vazio -> groups: fazer validação
														// apenas para registrationpost
	@Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
	@JsonView(UserView.RegistrationPost.class)
	@UserNameConstraint(groups = UserView.RegistrationPost.class)
	private String username;

	@NotBlank(groups = UserView.RegistrationPost.class)
	@Email(groups = UserView.RegistrationPost.class) // verificar se está no padrão de email
	@JsonView(UserView.RegistrationPost.class)
	private String email;

	@NotBlank(groups = { UserView.RegistrationPost.class, UserView.PasswordPut.class })
	@JsonView({ UserView.RegistrationPost.class, UserView.PasswordPut.class })
	@Size(min = 6, max = 20, groups = { UserView.RegistrationPost.class, UserView.PasswordPut.class })
	private String password;

	@NotBlank(groups = UserView.PasswordPut.class)
	@JsonView({ UserView.PasswordPut.class })
	@Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
	private String oldPassword;

	@JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
	private String fullName;

	@JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
	private String phoneNumber;

	@JsonView({ UserView.RegistrationPost.class, UserView.UserPut.class })
	private String cpf;

	@NotBlank(groups = UserView.ImagePut.class)
	@JsonView({ UserView.ImagePut.class })
	private String imageUrl;

}
