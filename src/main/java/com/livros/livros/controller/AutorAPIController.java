package com.livros.livros.controller;

import com.livros.livros.controller.response.HttpResponse;
import com.livros.livros.model.entities.Autor;
import com.livros.livros.service.impl.AutorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/autor")
public class AutorAPIController {

    private final AutorService autorService;
    private final Logger log = LogManager.getLogger(AutorAPIController.class);

    public AutorAPIController(AutorService autorService) {
        this.autorService = autorService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaAutor(){
        log.info(">>>> [Controller] consultaAutor iniciado");

        return ResponseEntity.ok().body(autorService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaAutorPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaAutorPorId iniciado");

        Optional<Autor> autor = autorService.findById(id);

        return ResponseEntity.ok().body(autor);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirAutor(@RequestBody Autor autor){
        log.info(">>>> [Controller] inserirAutor iniciado");

        return ResponseEntity.ok().body(autorService.insert(autor));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateAutor(@RequestBody Autor autor){
        log.info(">>>> [Controller] updateAutor iniciado");

        return ResponseEntity.ok().body(autorService.update(autor));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteAutor(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteAutor iniciado");

        autorService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Autor id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}
