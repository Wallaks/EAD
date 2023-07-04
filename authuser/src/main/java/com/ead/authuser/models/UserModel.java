package com.ead.authuser.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data // elimina escrever getters e setters (lombok) a nível de classe
@JsonInclude(Include.NON_NULL) // oculta varáveis com valores nulos a nível de classe
@Entity // entidade
@Table(name = "TB_USERS")

// vai fazer a conversão de objetos java para uma sequencia de bytes que pode ser salva no BD
public class UserModel implements Serializable {

	public static final long serialVersionUID = 1L; // número de controle de versionamento

	@Id // UUID ideal para trabalhar em sistemas distribuidos como este que está sendo feito
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@Column(nullable = false, unique = true, length = 50) // unique = true -> Nome de usuário unico para cada usuário
	private String username;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 255)
	@JsonIgnore // ignorar no json
	private String password;

	@Column(nullable = false, length = 150)
	private String fullName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 20)
	private String cpf;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") // pattern é o padrão de data
	private LocalDateTime creationDate;

	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime lastUpdateDate;

}
