package com.ead.authuser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;

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
		return userRepository.findById(userId);
	}

	@Override
	public void delete(UserModel userModel) {
		userRepository.delete(userModel);
	}

	@Override
	public void save(UserModel userModel) {
		userRepository.save(userModel);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Page<UserModel> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}
