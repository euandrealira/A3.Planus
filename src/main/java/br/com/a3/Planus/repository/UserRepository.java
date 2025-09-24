package br.com.a3.Planus.repository;

import br.com.a3.Planus.model.User;
import br.com.a3.Planus.model.enumerable.ProfileEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByProfile(ProfileEnum profile);
}