package dev.vandersonandrade.alunoonline.service;

import dev.vandersonandrade.alunoonline.model.Disciplina;
import dev.vandersonandrade.alunoonline.model.Professor;
import dev.vandersonandrade.alunoonline.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void CriarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinaDoProf(Long professorId) {
        return disciplinaRepository.findByProfessorId(professorId);
    }

    public List<Disciplina> listarTodasDisciplina() {
        return disciplinaRepository.findAll();
    }


    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplina) {

        Optional<Disciplina> disciplinaDoBancoDeDados = buscarDisciplinaPorId(id);

        if(disciplinaDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada no banco de dados!");
        }

        Disciplina disciplinaEditado = disciplinaDoBancoDeDados.get();

        disciplinaEditado.setNome(disciplina.getNome());
        disciplinaEditado.setCargaHoraria(disciplina.getCargaHoraria());
        disciplinaEditado.setProfessor(disciplina.getProfessor());

        disciplinaRepository.save(disciplinaEditado);

    }


}
