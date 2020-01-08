package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.model.Transacoes;
import com.conductorcrud.simpleconductorcrud.repository.TransacoesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/transacoes"})
public class TransacoesController {

    private TransacoesRepository transacoesRepository;

    public TransacoesController(TransacoesRepository transacoesRepository){
        this.transacoesRepository = transacoesRepository;
    }

    @GetMapping
    public List findAll(){
        return this.transacoesRepository.findAll();
    }

    @GetMapping(path = {"/{idTransacoes}"})
    public ResponseEntity findById(@PathVariable long idTransacoes){
        return transacoesRepository.findById(idTransacoes)
                .map(transacao -> ResponseEntity.ok().body(transacao))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transacoes create(@RequestBody Transacoes transacao){
        return transacoesRepository.save(transacao);
    }

    @PutMapping(value="/{idTransacoes}")
    public ResponseEntity update(@PathVariable("idTransacoes") long idTransacoes, @RequestBody Transacoes transacao){
        return transacoesRepository.findById(idTransacoes)
                .map(record -> {
                    record.setDataTransacao(transacao.getDataTransacao());
                    record.setValor(transacao.getValor());
                    Transacoes updatedTransacoes = transacoesRepository.save(record);
                    return ResponseEntity.ok().body(updatedTransacoes);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{idTransacoes}"})
    public ResponseEntity<?> delete(@PathVariable long idTransacoes) {
        return transacoesRepository.findById(idTransacoes)
                .map(transacao -> {
                    transacoesRepository.deleteById(idTransacoes);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
