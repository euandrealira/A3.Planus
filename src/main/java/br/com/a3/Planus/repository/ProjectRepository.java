package br.com.a3.Planus.repository;

import br.com.a3.Planus.model.Project;
import br.com.a3.Planus.model.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @EntityGraph(attributePaths = {"manager","teams"})
    @Query("select p from Project p")
    List<Project> findAllWithTeams();

    @EntityGraph(attributePaths = {"manager","teams"})
    @Query("select p from Project p where p.id = :id")
    Optional<Team> findByIdWithTeams(@Param("id") Long id);
}
