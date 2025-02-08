package dev.vandersonandrade.alunoonline.controller;

import dev.vandersonandrade.alunoonline.model.Disciplina;
import dev.vandersonandrade.alunoonline.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaService.CriarDisciplina(disciplina);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarTodasDisciplina(Disciplina disciplina) {
        return disciplinaService.listarTodasDisciplina();
    }

    @GetMapping("/professor/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarDisciplinasDoProf(@PathVariable Long professorId) {
        return disciplinaService.listarDisciplinaDoProf(professorId);
    }



}
