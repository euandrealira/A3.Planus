package br.com.a3.Planus.repository;

import br.com.a3.Planus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }