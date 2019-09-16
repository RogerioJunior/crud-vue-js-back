package br.com.crudTest.crudvueback.controller;

import br.com.crudTest.crudvueback.model.Tarefa;
import br.com.crudTest.crudvueback.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/tarefas"})
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    TarefaController(TarefaRepository tarefaRepository) {
        this.repository = tarefaRepository;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

//    @GetMapping(path = {"/{id}"})
//    public ResponseEntity findById(@PathVariable long id){
//        return repository.findById(id)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public Tarefa create(@RequestBody Tarefa tarefa){
        return repository.save(tarefa);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Tarefa tarefa) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(tarefa.getNome());
                    record.setDescricao(tarefa.getDescricao());
                    record.setNota(tarefa.getNota());
                    Tarefa updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
