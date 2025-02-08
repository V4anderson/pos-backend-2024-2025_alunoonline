package dev.vandersonandrade.alunoonline.repository;

import dev.vandersonandrade.alunoonline.model.Aluno;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
