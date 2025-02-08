package dev.vandersonandrade.alunoonline.controller;

import dev.vandersonandrade.alunoonline.dtos.AtualizarNotasRequest;
import dev.vandersonandrade.alunoonline.model.Disciplina;
import dev.vandersonandrade.alunoonline.model.MatriculaAluno;
import dev.vandersonandrade.alunoonline.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas-alunos")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatricula(@RequestBody MatriculaAluno matriculaAluno) {
        matriculaAlunoService.criarMatricula(matriculaAluno);
    }

    @PatchMapping("/trancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trancarMatricula(@PathVariable Long id){
        matriculaAlunoService.trancarMatricula(id);
    }

    @PatchMapping("/atualiza-notas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNotas(@PathVariable Long id,
                               @RequestBody AtualizarNotasRequest atualizarNotasRequest) {
        matriculaAlunoService.atualizarNotas(id, atualizarNotasRequest);

    }

}