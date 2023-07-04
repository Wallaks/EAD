package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // gerenciado pelo Spring
public class UserServiceImpl implements UserService {

	@Autowired // ponto de injeção para iniciar o UserRepository
	UserRepository userRepository;

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserModel> findbyId(UUID userId) {
		return userRepository.findAllById(userId);
	}


}
