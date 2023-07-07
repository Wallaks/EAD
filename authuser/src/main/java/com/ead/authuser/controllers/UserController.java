package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.dtos.UserDto.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;

@RestController // um bean gerenciado pelo Spring
@CrossOrigin(origins = "*", maxAge = 3600) // permite o acesso de todas as origens definindo um tempo a nível de classe
@RequestMapping("/users") // Uri, recurso deve ser bem definido. Http de forma semântica
public class UserController {

	@Autowired // injeção de dependencia
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll()); // listagem de usuários
	}

	@GetMapping("/{userId}") // Optional pois tem duas possíveis respostas
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID userId) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
		// Checar se existe
		if (!usermodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(usermodelOptional.get());
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
		if (!usermodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		} else {
			userService.delete(usermodelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
			@RequestBody @JsonView({ UserView.userPut.class }) UserDto userDto) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
		if (!usermodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		} else {
			// obter esse userModel
			var userModel = usermodelOptional.get();
			userModel.setFullName(userDto.getFullName());
			userModel.setCpf(userDto.getCpf());
			userModel.setCpf(userDto.getCpf());
			userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
			userService.save(userModel);

			return ResponseEntity.status(HttpStatus.OK).body(userModel);
		}
	}

	@PutMapping("/{userId}/password")
	public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
			@RequestBody @JsonView({ UserView.PasswordPut.class }) UserDto userDto) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
		if (!usermodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

		}
		if (!usermodelOptional.get().getPassword().equals(userDto.getOldPassword())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password");

		} else {
			var userModel = usermodelOptional.get();
			userModel.setPassword(userDto.getPassword());
			userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
			userService.save(userModel);

			return ResponseEntity.status(HttpStatus.OK).body("Passwords updated successfully");
		}
	}

	@PutMapping("/{userId}/image")
	public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
			@RequestBody @JsonView({ UserView.imagePut.class }) UserDto userDto) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
		if (!usermodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

		} else {
			var userModel = usermodelOptional.get();
			userModel.setImageUrl(userDto.getImageUrl());
			userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
			userService.save(userModel);

			return ResponseEntity.status(HttpStatus.OK).body(userModel);
		}
	}
}
