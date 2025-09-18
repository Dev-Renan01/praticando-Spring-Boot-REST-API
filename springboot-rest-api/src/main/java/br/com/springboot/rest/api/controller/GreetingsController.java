package br.com.springboot.rest.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Indica que essa classe é um controlador REST, ou seja, ela vai expor endpoints HTTP
@RestController
public class GreetingsController {

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
}