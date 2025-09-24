package br.com.a3.Planus.repository;

import br.com.a3.Planus.model.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @EntityGraph(attributePaths = "members")
    @Query("select t from Team t")
    List<Team> findAllWithMembers();

    @EntityGraph(attributePaths = "members")
    @Query("select t from Team t where t.id = :id")
    Optional<Team> findByIdWithMembers(@Param("id") Long id);
}
