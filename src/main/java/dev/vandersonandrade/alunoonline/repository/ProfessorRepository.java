package dev.vandersonandrade.alunoonline.repository;

import dev.vandersonandrade.alunoonline.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
