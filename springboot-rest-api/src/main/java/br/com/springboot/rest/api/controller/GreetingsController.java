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

    @RequestMapping(value = "/adicionar/{nome}/{idade}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
    public String salvarUsuario(@PathVariable String nome, @PathVariable int idade) {

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setIdade(idade);

        usuarioRepository.save(usuario); // Grava no banco de dados

        return "Usuário, " + nome + " adicionado com sucesso!";

    }

    @GetMapping(value = "listarTodos") // Mapeia requisições GET para o endpoint /listarTodos
    @ResponseBody // Retorna os dados para o corpo da resposta / retorna um Json
    public ResponseEntity<List<Usuario>> listarUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        // Retorna a lista de usuários em Json dentro de uma ResponseEntity com status HTTP 200 (OK)
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

}