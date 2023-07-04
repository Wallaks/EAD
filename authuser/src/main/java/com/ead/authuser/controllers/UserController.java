package com.ead.authuser.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/userId")
	public ResponseEntity<> getOneUser(@PathVariable(value = "userId") UUID userId) {
		Optional<UserModel> usermodelOptional = userService.findbyId(userId);
	}
}
