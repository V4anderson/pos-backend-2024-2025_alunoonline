package dev.vandersonandrade.alunoonline.service;

import dev.vandersonandrade.alunoonline.model.Aluno;
import dev.vandersonandrade.alunoonline.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> buscarAlunoPeloId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPeloId(Long id) {
       alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno) {

        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPeloId(id);

        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados!");
        }

        Aluno alunoEditado = alunoDoBancoDeDados.get();

        alunoEditado.setNome(aluno.getNome());
        alunoEditado.setEmail(aluno.getEmail());
        alunoEditado.setCpf(aluno.getCpf());

        alunoRepository.save(alunoEditado);
    }
}
