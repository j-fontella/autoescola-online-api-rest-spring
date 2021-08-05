package br.com.joaofontella.autoescolaonlinespringangular.controller;

import br.com.joaofontella.autoescolaonlinespringangular.dto.UsuarioDTO;
import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;
import br.com.joaofontella.autoescolaonlinespringangular.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public List<UsuarioDTO> getAllUsers(){
        var usuarios = usuarioRepository.findAll();

        return usuarios.
                stream().
                map((UsuarioDTO::converter)).
                collect(Collectors.toList());
    }

    @GetMapping("/id/{prk}")
    public UsuarioDTO getUserByPrk(@PathVariable("prk") Long prk){
        return UsuarioDTO.converter(usuarioRepository.getById(prk));
    }

    /*@GetMapping("/usuario/{usuario}")
    public ResponseEntity getUserByName(@PathVariable("usuario") String usuario){
        UsuarioDTO user = UsuarioDTO.converter(usuarioRepository.findByName(usuario));
        if(user.getUsuario().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NULL");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }*/


    @PostMapping("/logar")
    public ResponseEntity loginUser(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> user = usuarioRepository.findByName(usuarioDTO.getUsuario());
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não localizado");
        }
        Usuario u = user.get();
        if(!usuarioDTO.getSenha().equals(u.getSenha())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta");
        }

        return new ResponseEntity<>(usuarioDTO,HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity saveUser(@RequestBody UsuarioDTO usuarioDTO) {
        String erro = validarDTO(usuarioDTO);
        if(!erro.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
        Optional<Usuario> user = usuarioRepository.findByName(usuarioDTO.getUsuario());
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado na base de dados");
        }
        var u = new Usuario();
        u.setUsuario(usuarioDTO.getUsuario());
        u.setSenha(usuarioDTO.getSenha());
        usuarioRepository.save(u);
        return new ResponseEntity<>(usuarioDTO,HttpStatus.OK);
    }

    private String validarDTO(UsuarioDTO usuarioDTO){
        String erro = "";
        if (usuarioDTO.getUsuario().length() < 3) {
            erro += "Usuário deve ter 3 caracteres ou mais.";
        }
        if (usuarioDTO.getSenha().length() < 3) {
            erro += "Senha deve ter 3 caracteres ou mais";
        }
        return erro;
    }
}
