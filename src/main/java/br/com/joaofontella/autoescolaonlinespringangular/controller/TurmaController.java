package br.com.joaofontella.autoescolaonlinespringangular.controller;


import br.com.joaofontella.autoescolaonlinespringangular.dto.TurmaDTO;
import br.com.joaofontella.autoescolaonlinespringangular.dto.UsuarioCadastroDTO;
import br.com.joaofontella.autoescolaonlinespringangular.model.Turma;
import br.com.joaofontella.autoescolaonlinespringangular.repository.TurmaRepository;
import br.com.joaofontella.autoescolaonlinespringangular.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;


    public TurmaController(TurmaRepository turmaRepository, UsuarioRepository usuarioRepository) {
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/ativas")
    public ResponseEntity getTurmas(){
        var turmas = turmaRepository.findAll();

        return new ResponseEntity<>(turmas.
                stream().
                map((TurmaDTO::converter)).
                collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity saveUser(@RequestBody TurmaDTO turmaDTO) {
        String erro = validarDTO(turmaDTO);
        if(!erro.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        var t = new Turma();
        t.setDisciplina(turmaDTO.getDisciplina());
        t.setVagas(turmaDTO.getVagas());
        turmaRepository.save(t);
        return new ResponseEntity<>(turmaDTO,HttpStatus.OK);
    }

    @PostMapping("/turmasporaluno")
    public ResponseEntity getTurmasPorAluno(@RequestBody UsuarioCadastroDTO us){
        if(us.getPrk() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar usuário");
        }
        var turmas = turmaRepository.findByAluno(us.getPrk());
        return new ResponseEntity<>(turmas,HttpStatus.OK);
    }

    @GetMapping("/id/{prk}")
    public ResponseEntity getUsuariosPorTurma(@PathVariable("prk") Long frkTurma){
        var alunos = turmaRepository.findByTurma(frkTurma);
        if(alunos.isPresent()){
            var users = alunos.get();
            ArrayList<UsuarioCadastroDTO> userRet = new ArrayList<>();
            for (String user : users){
                String[] us = user.split(",");
                String nome = us[0];
                Long prk = Long.parseLong(us[1]);
                userRet.add(new UsuarioCadastroDTO(nome, prk));
            }
            return new ResponseEntity<>(userRet, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao carregar alunos");
    }

    @PostMapping("/removeraluno")
    public ResponseEntity removerAlunoTurma(@RequestBody UsuarioCadastroDTO us){
        turmaRepository.removerAlunoTurma(us.getFrkTurma(), us.getPrk());
        return new ResponseEntity<>(us, HttpStatus.OK);
    }

    @PostMapping("/elegiveis")
    public ResponseEntity getUsuariosElegiveis(@RequestBody List<Long> ls){
       if(ls.isEmpty()){
           var elegiveis = usuarioRepository.findAll();
           return new ResponseEntity<>(elegiveis, HttpStatus.OK);
       }
       var eletiveis = usuarioRepository.getUsuariosEletiveis(ls);
       if(eletiveis.isPresent()){
           var users = eletiveis.get();
           return new ResponseEntity<>(users, HttpStatus.OK);
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao carregar alunos eletiveis");
    }

    @PostMapping("/cadastraraluno")
    public ResponseEntity cadastrarAluno(@RequestBody UsuarioCadastroDTO us){
        String erro = "";
        if(us.getFrkTurma() == null){
            erro += "Turma inválida";
        }
        if(us.getPrk() == null){
            erro += " | Aluno inválido";
        }
        if(!erro.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
        Turma t = turmaRepository.getById(us.getFrkTurma());
        if(t.getVagas() < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Turma sem vagas");
        }
        turmaRepository.cadastrarUsuario(us);
        turmaRepository.removerVaga(us.getFrkTurma());
        return new ResponseEntity<>(us,HttpStatus.OK);
    }

    private String validarDTO(TurmaDTO turmaDTO){
        String erro = "";
        if (turmaDTO.getDisciplina().length() < 3) {
            erro += "Disciplina deve ter 3 caracteres ou mais.";
        }
        if (turmaDTO.getVagas() < 1) {
            erro += "Uma turma deve ter pelo menos 1 vaga.";
        }
        return erro;
    }
}
