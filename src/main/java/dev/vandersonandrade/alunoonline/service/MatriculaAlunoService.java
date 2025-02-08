package dev.vandersonandrade.alunoonline.service;

import dev.vandersonandrade.alunoonline.dtos.AtualizarNotasRequest;
import dev.vandersonandrade.alunoonline.enums.MatriculaAlunoStatusEnum;
import dev.vandersonandrade.alunoonline.model.MatriculaAluno;
import dev.vandersonandrade.alunoonline.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatriculaAlunoService {

    private static final double MEDIA_PARA_APROVACAO = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId) {
        MatriculaAluno matriculaAluno = buscarMatriculaOuLancarExcecao(matriculaAlunoId);

        if(matriculaAluno.getStatus()
                .equals(MatriculaAlunoStatusEnum.MATRICULADO)){
            matriculaAluno.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível trancar uma matricula com o status MATRICULADO");
        }
    }

    public void atualizarNotas(Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest) {
        MatriculaAluno matriculaAluno = buscarMatriculaOuLancarExcecao(matriculaAlunoId);

        if(atualizarNotasRequest.getNota1() != null) {
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }
        if(atualizarNotasRequest.getNota2() != null) {
            matriculaAluno.setNota2(atualizarNotasRequest.getNota2());
        }

        calcularMediaEModificarStatus(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    private MatriculaAluno buscarMatriculaOuLancarExcecao(Long matriculaAlunoId) {
        return matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula Aluno não encontrada"));
    }

    private void calcularMediaEModificarStatus(MatriculaAluno matriculaAluno) {
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if(nota1 != null && nota2 != null) {
            double media = (nota1 + nota2) / 2;
            matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ?
                    MatriculaAlunoStatusEnum.APROVADO :
                    MatriculaAlunoStatusEnum.REPROVADO);
        }
    }

}