package dev.vandersonandrade.alunoonline.repository;

import dev.vandersonandrade.alunoonline.model.Aluno;
import dev.vandersonandrade.alunoonline.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
@RestController
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByProfessorId(Long professorId);

}
