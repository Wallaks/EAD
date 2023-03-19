package com.ead.authuser.models;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/* elimina escrever getters e setters */
@Data
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
/* vai fazer a conversão de objetos java para uma sequencia de bytes que pode ser salva no BD */
public class UserModel implements Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;
	@Column(nullable = false, unique = true, length = 50)
	private String username;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, length = 255)
	@JsonIgnore
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
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;
	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime lastUpdateDate;

}
