package com.ead.authuser.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ead.authuser.models.UserModel;

// JpaRepository, evita de ficar escrevendo blocos sql
// Esse será um Bean que o Spring gerenciará (não precisa anotar @Bean)
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
