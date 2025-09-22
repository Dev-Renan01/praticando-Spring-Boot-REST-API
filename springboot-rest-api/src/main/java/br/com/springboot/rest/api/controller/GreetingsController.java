package br.com.springboot.rest.api.controller;

import br.com.springboot.rest.api.model.Usuario;
import br.com.springboot.rest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indica que essa classe é um controlador REST, ou seja, ela vai expor endpoints HTTP
@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mapeia requisições HTTP GET para a URL /{name}
    @RequestMapping(value = "/nome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK) // Define o status HTTP de retorno como 200 OK
    public String greetigText(@PathVariable String name){ // @PathVariable captura o valor da URL e associa à variável "name"

        // Retorna uma string simples como resposta da API
        return "Spring Boot REST API: " + name + "!";
    }

    @RequestMapping(value = "/soma/{n1}/{n2}", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public String soma(@PathVariable  int n1, @PathVariable int n2){
        double soma = n1 + n2;
        return n1 + " + " + n2 + " = " + soma;
    }


    @GetMapping(value = "listarTodos") // Mapeia requisições GET para o endpoint /listarTodos
    @ResponseBody // Retorna os dados para o corpo da resposta / retorna um Json
    public ResponseEntity<List<Usuario>> listarUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        // Retorna a lista de usuários em Json dentro de uma ResponseEntity com status HTTP 200 (OK)
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping (value = "salvar") // Mapea a URl
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ // Recebe os dados para salvar

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long id){

        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>( "User deletado com sucesso!", HttpStatus.OK);

    }

    @GetMapping(value = "buscarporid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarporid(@RequestParam(name = "id") Long id){

        Usuario user = usuarioRepository.findById(id).get();

        return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
        if(usuario.getId() == null){
            return new ResponseEntity<String>("Por favor, informe o Id!", HttpStatus.OK);

        }
        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){

        List<Usuario> user = usuarioRepository.buscarPorNome(nome);

        return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
}