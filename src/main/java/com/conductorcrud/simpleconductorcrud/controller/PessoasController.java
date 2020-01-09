package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/pessoas"})
public class PessoasController {

    private PessoasRepository pessoasRepository;

    public PessoasController(PessoasRepository pessoasRepository){
        this.pessoasRepository = pessoasRepository;
    }

    @GetMapping
    public List findAll(){
        return this.pessoasRepository.findAll();
    }

    @GetMapping(path = {"/{idPessoa}"})
    public ResponseEntity findById(@PathVariable long idPessoa){
        return pessoasRepository.findById(idPessoa)
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoas create(@RequestBody Pessoas pessoa){
        return pessoasRepository.save(pessoa);
    }

    @PutMapping(value="/{idPessoa}")
    public ResponseEntity update(@PathVariable("idPessoa") long idPessoa, @RequestBody Pessoas pessoa){
        return pessoasRepository.findById(idPessoa)
                .map(record -> {
                    if (pessoa.getNome() != null)    record.setNome(pessoa.getNome());
                    if (pessoa.getCpf() != null)    record.setCpf(pessoa.getCpf());
                    if (pessoa.getDataNascimento() != null)    record.setDataNascimento(pessoa.getDataNascimento());
                    Pessoas updatedPessoa = pessoasRepository.save(record);
                    return ResponseEntity.ok().body(updatedPessoa);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{idPessoa}"})
    public ResponseEntity<?> delete(@PathVariable long idPessoa) {
        return pessoasRepository.findById(idPessoa)
                .map(pessoa -> {
                    pessoasRepository.deleteById(idPessoa);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
