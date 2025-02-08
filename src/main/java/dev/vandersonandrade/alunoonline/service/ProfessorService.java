package dev.vandersonandrade.alunoonline.service;

import dev.vandersonandrade.alunoonline.model.Aluno;
import dev.vandersonandrade.alunoonline.model.Professor;
import dev.vandersonandrade.alunoonline.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);
    }
    public List<Professor> listarTodosProfessor(Professor professor) {
        return professorRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    @DeleteMapping
    public void deletarProfessorPorId(Long id){
        professorRepository.deleteById(id);
    }

    public void atualizarProfessorPorId(Long id, Professor professor) {
        Optional<Professor> professorDoBancoDedados = buscarProfessorPorId(id);

        if(professorDoBancoDedados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado no banco de dados!" );
        }

        Professor professorEditado = professorDoBancoDedados.get();

        professorEditado.setCpf(professor.getCpf());
        professorEditado.setNome(professor.getNome());
        professorEditado.setEmail(professor.getEmail());

        professorRepository.save(professorEditado);

    }
}
