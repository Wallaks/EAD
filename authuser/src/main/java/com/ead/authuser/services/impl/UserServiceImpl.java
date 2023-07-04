package com.ead.authuser.services.impl;

import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // gerenciado pelo Spring
public class UserServiceImpl implements UserService {

	@Autowired // ponto de injeção para iniciar o UserRepository
	UserRepository userRepository;

}
