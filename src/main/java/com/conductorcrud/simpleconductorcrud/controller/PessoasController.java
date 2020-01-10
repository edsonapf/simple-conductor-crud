package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity createAccount(@RequestBody Pessoas pessoa){
        if(pessoasRepository.findByCpf(pessoa.getCpf()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe!");

        pessoasRepository.save(pessoa);
        return ResponseEntity.ok().body("Usuário criado com sucesso!");
    }

    @PutMapping(value="/{idPessoa}")
    public ResponseEntity updateAccount(@PathVariable("idPessoa") long idPessoa, @RequestBody Pessoas pessoa){
        return pessoasRepository.findById(idPessoa)
                .map(record -> {
                    if (pessoa.getNome() != null || pessoa.getNome().isEmpty())    record.setNome(pessoa.getNome());
                    if (pessoa.getCpf() != null || pessoa.getNome().isEmpty())    record.setCpf(pessoa.getCpf());
                    if (pessoa.getDataNascimento() != null)    record.setDataNascimento(pessoa.getDataNascimento());
                    Pessoas updatedPessoa = pessoasRepository.save(record);
                    return ResponseEntity.ok().body("Usuário atualizado!");
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
